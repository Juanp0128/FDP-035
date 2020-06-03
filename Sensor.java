import java.util.ArrayList;

public class Sensor {
        private String tipo;
        private double valor;
        public static ArrayList<Sensor> sensores = new ArrayList<>();

        public Sensor()
        {

        }
        public Sensor(String t, double v)
        {
            this.tipo = t;
            this.valor = v;
            sensores.add(this);
        }
        @Override
        public String toString() {
            return "Sensor [tipo=" + tipo + ", valor=" + valor + "]";
        }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static Sensor[] OrdenTemperatura(){
            ArrayList<Sensor> ordenado = (ArrayList) sensores.clone();

            for(int z = 0; z<ordenado.size();z++){
                for(int i=0;i<ordenado.size()-z-1;i++){
                    if (ordenado.get(i).getValor()> ordenado.get(i+1).getValor()){
                        Sensor temp = ordenado.get(i+1);
                        ordenado.set(i+1,ordenado.get(i));
                        ordenado.set(i,temp);
                    }
                }
            }
            int contador=0;
            for(int i=0;i<ordenado.size();i++){
                if (ordenado.get(i).getTipo().equalsIgnoreCase("Temperatura")){
                    contador++;
                }
            }
            Sensor[] Final = new Sensor[contador];
            contador=0;
            for (int i=0;i<ordenado.size();i++){
                if (ordenado.get(i).getTipo().equalsIgnoreCase("Temperatura")){
                    Final[contador]=ordenado.get(i);
                    contador++;
                }
            }
            return Final;
        }
    }
