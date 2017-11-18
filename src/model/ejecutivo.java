package model;

public final class ejecutivo extends persona{
	private String ejeFecContrato;

	public ejecutivo() {		super(); 	}

	public ejecutivo(String perRut, 
			String perNombre, 
			String perApePaterno, 
			String perApeMaterno,
			String perNacionalidad, 
			String perFecNacimiento, 
			String ejeFecContrato) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, perNacionalidad, perFecNacimiento);
		this.ejeFecContrato = ejeFecContrato;
	}

	public String getEjeFecContrato() {		return ejeFecContrato;	}
	public void setEjeFecContrato(String ejeFecContrato) {		this.ejeFecContrato = ejeFecContrato;	}

	@Override
	public String toString() {
		return "ejecutivo [ejeFecContrato=" + ejeFecContrato 
				+ ", perRut=" + perRut 
				+ ", perNombre=" + perNombre
				+ ", perApePaterno=" + perApePaterno 
				+ ", perApeMaterno=" + perApeMaterno 
				+ ", perNacionalidad="+ perNacionalidad 
				+ ", perFecNacimiento=" + perFecNacimiento + "]";
	}

	/* (non-Javadoc)
	 * @see model.persona#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
	
}
