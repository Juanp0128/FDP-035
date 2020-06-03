import java.util.ArrayList;

public class Vehiculo {
    private int modelo;
    private String marca;
    private double valorComercial;
    private String color;
    private int id;
    public static int idActual = 1;
    private ArrayList<Sensor> sensores = new ArrayList<Sensor>();
    public static ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

    public Vehiculo() {
        vehiculos.add(this);
        id = idActual;
        idActual++;
    }

    public Vehiculo(int mo, String ma, double va) {
        this(mo,ma,va,"Verde");
    }

    public Vehiculo(int mo, String ma, double va, String co) {
        vehiculos.add(this);
        this.modelo = mo;
        this.marca = ma;
        this.valorComercial = va;
        this.color = co;
        id = idActual;
        idActual++;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(double valorComercial) {
        this.valorComercial = valorComercial;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(ArrayList<Sensor> sensores) {
        this.sensores = sensores;
    }

    @Override
    public String toString() {
        String Listadesensores = "";
        for(Sensor sensor : sensores){
            Listadesensores += "\n"+sensor.toString();
        }

        return "Vehiculo: \n" +
                "modelo: " + modelo +
                "\nMarca: " + marca +
                "\nValor Comercial: " + valorComercial +
                "\nColor: " + color+
                "\nID: "+id + "\n"+"Lista de Sensores: \n"+ Listadesensores;
    }

    public static String toStringVehiculos(){
        String Listadevehiculos = "";
        for(Vehiculo v : vehiculos){
            Listadevehiculos += v.toString();
        }
        return Listadevehiculos;
    }

    public static int cantidadVehiculos() {
        return vehiculos.size();
    }
    public  int cantidadSensores() {
        return sensores.size();
    }
    public void añadirSensor(Sensor s){
        sensores.add(s);
    }
    public Vehiculo obtenerVehiculoPorId(int identi)
    {
        int i = 0;
        Vehiculo v = null;
        while(i<vehiculos.size())
        {
            if(vehiculos.get(i).getId()==identi)
            {
                v = vehiculos.get(i);
                break;
            }
            else
            {
                v = null;
            }
            i++;
        }
        return v;
    }
    public int getId() {
        return id;
    }
    public String sensorInd(int p)
    {
        String s = null;
        if(p < sensores.size())
        {
            for(int i = p; i < sensores.size();i++)
            {
                if(i<sensores.size())
                {
                    s = sensores.get(i).getTipo();
                    break;
                }
                else
                {
                    break;
                }

            }
        }
        return s;
    }
    public Sensor[] organizar()
    {
        Sensor[] sen = new Sensor[sensores.size()];
        sen[0] = sensores.get(0);
        for(int i = 1; i< sensores.size();i++ )
        {
            sen[i] = sensores.get(i);
            if(sen[i].getValor()<sen[i-1].getValor())
            {
                sen[i-1] = sen[i];
                sen[i] =sensores.get(i-1);
            }
        }
        return sen;
    }
}

