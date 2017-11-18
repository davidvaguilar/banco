package model;

public final class natural extends cliente {
	private Integer natPatrimonio;

	public natural() {		super();	}

	public natural(String perRut, 
			String perNombre, 
			String perApePaterno, 
			String perApeMaterno, 
			String perNacionalidad,
			String perFecNacimiento, 
			String cliCategoria, 
			ejecutivo eje, 
			Integer natPatrimonio) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, perNacionalidad, perFecNacimiento, cliCategoria, eje);
		this.natPatrimonio = natPatrimonio;
	}
	
	public Integer getNatPatrimonio() {		return natPatrimonio;	}
	public void setNatPatrimonio(Integer natPatrimonio) {		this.natPatrimonio = natPatrimonio;	}

	@Override
	public String toString() {
		return "natural [natPatrimonio=" + natPatrimonio 
				+ ", cliCategoria=" + cliCategoria 
				+ ", eje=" + eje
				+ ", perRut=" + perRut 
				+ ", perNombre=" + perNombre 
				+ ", perApePaterno=" + perApePaterno
				+ ", perApeMaterno=" + perApeMaterno 
				+ ", perNacionalidad=" + perNacionalidad 
				+ ", perFecNacimiento="	+ perFecNacimiento + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		natural other = (natural) obj;
		if (perRut == null) {
			if (other.perRut != null)
				return false;
		} else if (!perRut.equals(other.perRut))
			return false;
		return true;
	}
	
	
	
}
