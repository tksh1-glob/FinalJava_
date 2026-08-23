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

    @Override
    public double calcularSalario(){
        final double factorExperiencia = 1.10;
        return salarioBase * (aniosExperiencia * factorExperiencia);
    }
    @Override
    public String getTipo(){
        return "Tiempo completo";
    }
    
    @Override
    public String toString(){
        return super .toString() + String.format(" | Experiencia: %.1f años", aniosExperiencia);
    }
}
