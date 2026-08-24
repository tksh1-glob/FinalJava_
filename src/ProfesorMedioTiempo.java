public class ProfesorMedioTiempo extends Profesor{
    private double horasActivasSemana;

    public ProfesorMedioTiempo(String nombre, double salarioBase, double horasActivasSemana){
        super(nombre, salarioBase);
        this.horasActivasSemana = horasActivasSemana;
    }

    public double getHorasActivasSemana(){
        return horasActivasSemana;
    }

    public void setHorasActivasSemana(double horasActivasSemana){
        this.horasActivasSemana = horasActivasSemana;
    }

     @Override
    public double calcularSalario(){
        return salarioBase * horasActivasSemana;
     }

     @Override
    public String getTipo(){
        return "Medio Tiempo";
     }

     @Override
    public String toString(){
        return super.toString() + String.format(" | Horas activas/sem: %.1f", horasActivasSemana);
     }
}
