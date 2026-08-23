public class ProfesorTiempoCompleto extends Profesor{
    private double aniosExperiencia;
    public ProfesorTiempoCompleto(String nombre, double salarioBase, double aniosExperiencia){
        super(nombre, salarioBase);
        this.aniosExperiencia = aniosExperiencia;
    }

    public double getAniosExperiencia(){
        return aniosExperiencia;
    }

    public void setAniosExperiencia(double aniosExperiencia){
        this.aniosExperiencia = aniosExperiencia;
    }

