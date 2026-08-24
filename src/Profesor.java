public abstract class Profesor {
    private static int contadorProfesores = 0;

    private final int id;
    private String nombre;
    protected double salarioBase;

    protected Profesor(String nombre, double salarioBase){
        this.id = ++contadorProfesores;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    //Parte estatico
    public static int getContadorProfesores(){
        return contadorProfesores;
    }
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public double getSalarioBase(){
        return salarioBase;
    }
    public void setSalarioBase(double salarioBase){
        this.salarioBase = salarioBase;
    }
    //Abstracts
    public abstract double calcularSalario();
    public abstract String getTipo();

    @Override
    public String toString(){
        return String.format(
                "[#%d] %-20s | Tipo: %-12s | Salario base: %10.2f | Salario calculado: %10.2f",
                id, nombre, getTipo(), salarioBase, calcularSalario()
        );
    }
}

