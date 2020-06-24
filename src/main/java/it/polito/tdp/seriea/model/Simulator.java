package it.polito.tdp.seriea.model;

import it.polito.tdp.seriea.model.Event;
import it.polito.tdp.seriea.model.Event.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.seriea.db.SerieADAO;

public class Simulator {
	private SerieADAO dao ;
	
	public Simulator () {
		this.dao = new SerieADAO ();
	}

	
	//MODELLO DEL MONDO
	private List<Risultati> risultati; 
	private Integer scartoReti;
	
	//PARAMETRI DI SIMULAZIONE
	private Integer P;
	private Integer tifosiSpostati;
	private Double probabilitaRibaltamento;
	
	//RISULTATI CALCOLATI
	private Integer nRibaltate;
	private SquadraTifosi nTifosiSquadra1;
	private SquadraTifosi nTifosiSquadra2;
	
	public Integer getnRibaltate() {
		return nRibaltate;
	}
	public void setnRibaltate(Integer nRibaltate) {
		this.nRibaltate = nRibaltate;
	}
	public SquadraTifosi getnTifosiSquadra1() {
		return nTifosiSquadra1;
	}
	public void setnTifosiSquadra1(SquadraTifosi nTifosiSquadra1) {
		this.nTifosiSquadra1 = nTifosiSquadra1;
	}
	public SquadraTifosi getnTifosiSquadra2() {
		return nTifosiSquadra2;
	}
	public void setnTifosiSquadra2(SquadraTifosi nTifosiSquadra2) {
		this.nTifosiSquadra2 = nTifosiSquadra2;
	}


	//CODA DEGLI EVENTI
	private PriorityQueue <Event> queue ;
	
	//INIT
	public void init(Team squadra1, Team squadra2) {
		this.risultati = new ArrayList <> (dao.risultati(squadra1, squadra2));
		this.nRibaltate=0;
		/*this.nTifosiSquadra1= new SquadraTifosi(squadra1.getTeam(), 1000);
		this.nTifosiSquadra2= new SquadraTifosi(squadra2.getTeam(), 1000);*/
		this.nTifosiSquadra1.setSquadra(squadra1.getTeam());
		this.nTifosiSquadra2.setSquadra(squadra2.getTeam());
		this.nTifosiSquadra1.setTifosi(1000);
		this.nTifosiSquadra2.setTifosi(1000);
		this.P=10;
		this.tifosiSpostati=0;
		this.scartoReti=0;
		this.probabilitaRibaltamento=0.0;
		
		this.queue =  new PriorityQueue<> ();

		for (Risultati r : this.risultati) {
			Event e = new Event (r.getData(), r, EventType.PARTITA, r.getHomeTeam(), r.getAwayTeam());
			this.queue.add(e);
		}
	}
	
	//RUN
	public void run() {
		 while(!this.queue.isEmpty()) {
			 Event e = this.queue.poll();
			 System.out.println(e);
			 processEvent(e);
		 }
	}

	//RICORDATI CHE AL PAREGGIO NON SUCCEDE NIENTE
	
	
	//SQUADRA 1 E' HOME TEAM 
	
	//PROCESSEVENT
	private void processEvent(Event e) {
		switch(e.getType()) {
		case PARTITA:
			//se il numero di tifosi è uguale, vedo chi ha vinto e schedulo un evento spostamento tifosi
			if (nTifosiSquadra1.getTifosi()==nTifosiSquadra2.getTifosi()) {
				Event e1 = new Event (e.getData(), e.getRisultato(), EventType.SPOSTAMENTO_TIFOSI, e.getSquadra1(),e.getSquadra2());
				queue.add(e1);
			} else {//se il numero di tifosi è diverso schedulo ribaltamento 
				Event e1 = new Event (e.getData(), e.getRisultato(), EventType.RIBALTAMENTO, e.getSquadra1(),e.getSquadra2());
				queue.add(e1);
			}
			break;
					
		case RIBALTAMENTO:
			//IN OGNI CASO SCHEDULARE SPOSTAMENTO TIFOSI
			//se squadra più tifosi vinto storicamente nessun cambiamento
			if (e.getSquadra1().compareTo(nTifosiSquadra1.getSquadra())==0 && e.getSquadra2().compareTo(nTifosiSquadra2.getSquadra())==0) {
				if (e.getRisultato().getGoalCasa()>e.getRisultato().getGoalFuori()) {
					if (nTifosiSquadra1.getTifosi()>nTifosiSquadra2.getTifosi()) {
						Event e1 = new Event (e.getData(), e.getRisultato(), EventType.SPOSTAMENTO_TIFOSI, e.getSquadra1(),e.getSquadra2());
						queue.add(e1);
						break;
					}
				}
			}
			//ho i ruoli invertiti ma comunque non ho ribaltamento
			if (e.getSquadra1().compareTo(nTifosiSquadra2.getSquadra())==0 && e.getSquadra2().compareTo(nTifosiSquadra1.getSquadra())==0) {
				if (e.getRisultato().getGoalCasa()<e.getRisultato().getGoalFuori()) {
					if (nTifosiSquadra1.getTifosi()<nTifosiSquadra2.getTifosi()) {
						Event e1 = new Event (e.getData(), e.getRisultato(), EventType.SPOSTAMENTO_TIFOSI, e.getSquadra1(),e.getSquadra2());
						queue.add(e1);
						break;
					}
				}
			}
			
			//se squadra più tifosi perso storicamente
			//-> 50% probabilita ribaltamento
			//->50% probabilita invariato
			this.probabilitaRibaltamento = Math.random();
			if (e.getSquadra1().compareTo(nTifosiSquadra1.getSquadra())==0 && e.getSquadra2().compareTo(nTifosiSquadra2.getSquadra())==0) {
				if (e.getRisultato().getGoalCasa()<e.getRisultato().getGoalFuori()) { //la squadra in casa ha perso ma ha più tifosi
					if (nTifosiSquadra1.getTifosi()>nTifosiSquadra2.getTifosi()) {
						if (probabilitaRibaltamento>0.5) { //avviene un ribaltamento
							e.getRisultato().setGoalCasa(e.getRisultato().getGoalFuori());
							e.getRisultato().setGoalFuori(e.getRisultato().getGoalCasa());
							Event e1 = new Event (e.getData(), e.getRisultato(), EventType.SPOSTAMENTO_TIFOSI, e.getSquadra1(),e.getSquadra2());
							queue.add(e1);
							this.nRibaltate++;
							break;
						} else { //non avviene ribaltamento
							Event e1 = new Event (e.getData(), e.getRisultato(), EventType.SPOSTAMENTO_TIFOSI, e.getSquadra1(),e.getSquadra2());
							queue.add(e1);
							break;
						}
					}
				}
			} 
			 //ho i ruoli invertiti
			if (e.getSquadra1().compareTo(nTifosiSquadra2.getSquadra())==0 && e.getSquadra2().compareTo(nTifosiSquadra1.getSquadra())==0) {
				if (e.getRisultato().getGoalCasa()>e.getRisultato().getGoalFuori()) { //la squadra fuori casa ha perso, ma ha più tifosi
					if (nTifosiSquadra1.getTifosi()<nTifosiSquadra2.getTifosi()) {
						if (probabilitaRibaltamento>0.5) { //avviene un ribaltamento
							e.getRisultato().setGoalCasa(e.getRisultato().getGoalFuori());
							e.getRisultato().setGoalFuori(e.getRisultato().getGoalCasa());
							Event e1 = new Event (e.getData(), e.getRisultato(), EventType.SPOSTAMENTO_TIFOSI, e.getSquadra1(),e.getSquadra2());
							queue.add(e1);
							this.nRibaltate++;
							break;
						} else { //non avviene ribaltamento
							Event e1 = new Event (e.getData(), e.getRisultato(), EventType.SPOSTAMENTO_TIFOSI, e.getSquadra1(),e.getSquadra2());
							queue.add(e1);
							break;
						}
					}
				}
			}
			break;
				
			
		case SPOSTAMENTO_TIFOSI:
			//CALCOLO LO SCARTO DI RETI
			if (e.getRisultato().getGoalCasa()>e.getRisultato().getGoalFuori()) {
				this.scartoReti= e.getRisultato().getGoalCasa()-e.getRisultato().getGoalFuori();
				//calcolo il numero di tifosi che si spostano che passno dalla squadra che ha fatto meno goal a quella che ha fatto più goal
				
				if (e.getSquadra1().compareTo(nTifosiSquadra1.getSquadra())==0 && e.getSquadra2().compareTo(nTifosiSquadra2.getSquadra())==0) {
					this.tifosiSpostati= ((this.P*this.scartoReti)/100)*nTifosiSquadra2.getTifosi();
					//squadra 1 avrà più tifosi
					this.nTifosiSquadra1.setTifosi(this.nTifosiSquadra1.getTifosi()+tifosiSpostati);
					this.nTifosiSquadra2.setTifosi(this.nTifosiSquadra2.getTifosi()-tifosiSpostati);
					break;
				} 
			}
				
			if (e.getRisultato().getGoalCasa()<e.getRisultato().getGoalFuori()) {
				this.scartoReti= e.getRisultato().getGoalFuori()-e.getRisultato().getGoalCasa();
				
				if (e.getSquadra1().compareTo(nTifosiSquadra2.getSquadra())==0 && e.getSquadra2().compareTo(nTifosiSquadra1.getSquadra())==0) {
					this.tifosiSpostati= ((this.P*this.scartoReti)/100)*nTifosiSquadra1.getTifosi();
					this.nTifosiSquadra1.setTifosi(this.nTifosiSquadra1.getTifosi()-tifosiSpostati);
					this.nTifosiSquadra2.setTifosi(this.nTifosiSquadra2.getTifosi()+tifosiSpostati);
					break;
				} 
			}

			break;
		}
	}
	
}
