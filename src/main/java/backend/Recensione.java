package backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Recensione {
	
	private LocalDate data;
	private String dataS;
	private String desc;
	private float voto;
	private int id;
	
	public Recensione(LocalDate data, String desc, float voto) {
		super();
		this.data = data;
		this.desc = desc;
		this.voto = voto;
	}
	@Override
	public String toString() {
		return "Recensione [data=" + data + ", desc=" + desc + ", voto=" + voto + "]";
	}
	public Recensione(String d, int v, String da) {
		this.desc = d;
		this.voto = v;
		this.dataS = da;
		this.data = LocalDate.parse(da, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getVoto() {
		return voto;
	}
	public void setVoto(float voto) {
		this.voto = voto;
	}
	public String getDataS() {
		return dataS;
	}
	public void setDataS(String dataS) {
		this.dataS = dataS;
	}
	

}