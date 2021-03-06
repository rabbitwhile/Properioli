package Hidato;

import java.util.ArrayList;
import javafx.util.Pair;
import java.util.Vector;

public class Cela{
	private String tipus;
	private boolean valida;
	private boolean visible;

	private String valor;


	//Constructora

	public Cela(){};

	public Cela(String tip, String val, boolean vis, boolean vali){
		this.tipus = tip;
		this.visible = vis;
		this.valor = val;
		this.valida = vali;
	}

	//Consultora
	public String getValor() {
		return valor;
	}

	public boolean isValida() {
		return valida;
	}

	public boolean isVisible() {
		return visible;
	}


	//modificadores
	public void ModificarValor(String x){
		this.valor = x;
	}


	////////////////////////
	//////	EXTENSIO ///////
	////////////////////////


	protected Integer CoordI;
	protected Integer CoordJ;
	protected String Adjacencia;

	protected Double[] Probabilitat;

	protected Integer PosicioNextCela = -1;
	protected Integer NumeroProbabilitats0 = 0;


	public void SetCoordI(Integer i){
		CoordI = i;
	}

	public Integer GetCoordI(){
		return CoordI;
	}

	public void SetCoordJ(Integer j){
		CoordJ = j;
	}

	public Integer GetCoordJ(){
		return CoordJ;
	}

	public String GetAdjacencia(){
		return Adjacencia;
	}

	public void SetAdjacencia(String adj){
		this.Adjacencia = adj;
	}
	/*
	protected ArrayList<Cela> Veins(String direccio) {
		return null;
	}
	*/
	protected void Veins(String direccio){}


	protected Cela NextCela() {
		return null;
	}

	protected boolean UpdateProbabilitat(){
		Double d = Probabilitat[PosicioNextCela];
		Probabilitat[PosicioNextCela] = 0.0;
		++NumeroProbabilitats0;
		if(NumeroProbabilitats0 >= Probabilitat.length) return false;
		for(Integer i = 0; i < Probabilitat.length; ++i){
			if(Probabilitat[i] != 0.0) {
				Probabilitat[i] += d / (Probabilitat.length - NumeroProbabilitats0);
			}
		}
		return true;
	}


}
