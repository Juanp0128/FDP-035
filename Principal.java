import java.io.File;
import java.util.Scanner;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Principal {
	
    public static final String url = "https://www.carroya.com/buscar/vehiculos/medellin/t4c239.do";
    public static final int maxPaginas = 2;

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        System.out.println();
        Vehiculo v1 = new Vehiculo();
        Scanner reader = new Scanner(System.in);
        int num = 30;
        System.out.println("\nMenu: \n" +
                "1. Añadir un Vehiculo\n" +
                "2. Mostrar la informacion de los Vehiculos almacenados\n" +
                "3. Mostrar la cantidad de Vehiculos almacenados\n" +
                "4. Mostrar la informacion de los Vehiculos de color Verde\n" +
                "5. Buscar Vehiculo mediante su ID\n" +
                "6. Asignar sensores a un Vehiculo\n" +
                "7. Mostrar los sensores de cierto Vehiculo\n" +
                "8. Mostrar los sensores de Temperatura\n" +
                "9. Mostrar la informacion del Vehiculo con mas sensores\n" +
                "10. Mostrar la informacion de los Vehiculos almacenados en el archivo .txt\n" +
                "666. Mostrar los sensores ordenados por valor\n" +
                "777. Añadir Vehiculos desde la Web CarroYa\n" +
                "0. Finalizar Programa");
        while (num != 0) {
            System.out.println("Ingrese el Numero: ");
            num = reader.nextInt();
            if (num == 1) {
                System.out.println("Ingrese el modelo del Vehiculo");
                int mo = reader.nextInt();
                System.out.println("Ingrese la marca del Vehiculo");
                reader.nextLine();
                String ma = reader.nextLine();
                System.out.println("Ingrese el valor del Vehiculo");
                double va = reader.nextDouble();
                System.out.println("Ingrese el color del Vehiculo");
                reader.nextLine();
                String co = reader.nextLine();
                Vehiculo v2 = new Vehiculo(mo, ma, va, co);
                System.out.println("Vehiculo Guardado!\n");
            } else if (num == 2) {
                System.out.print(v1.toStringVehiculos());

            } else if (num == 3) {
                System.out.println("Hay " + v1.cantidadVehiculos() + " Vehiculos Almacenados\n");

            } else if (num == 4) {
                for (int i = 1; i < v1.vehiculos.size(); i++) {
                    if (v1.vehiculos.get(i).getColor().equals("verde")) {
                        System.out.print(v1.vehiculos.get(i).toString());
                    }
                }
            } else if (num == 5) {
                System.out.println("Ingrese el ID del Vehiculo");
                int id = reader.nextInt();
                try {
                    System.out.println(v1.obtenerVehiculoPorId(id));
                } catch (Exception e) {
                    System.out.println("ID no correspondiente a ningun Vehiculo");
                    System.out.println("Excepción: " + e.getMessage() + "\n");
                }
            } else if (num == 6) {
                System.out.print("Ingrese el ID del Vehiculo al que desea añadirle el Sensor");
                int id = reader.nextInt();
                System.out.print("Asigne el Tipo de Sensor");
                reader.nextLine();
                String t = reader.nextLine();
                System.out.print("Asigne el valor del Sensor");
                double v = reader.nextDouble();

                Sensor s1 = new Sensor(t, v);
                v1.obtenerVehiculoPorId(id).añadirSensor(s1);


            } else if (num == 7) {
                System.out.println("Ingrese el ID del Vehiculo");
                int id = reader.nextInt();
                System.out.println((v1.obtenerVehiculoPorId(id).getSensores()));

            } else if (num == 8) {
                for (int i = 0; i < v1.vehiculos.size(); i++) {
                    for (int j = 0; j < v1.vehiculos.get(i).cantidadSensores(); j++) {
                        if (v1.vehiculos.get(i).sensorInd(j).equals("temperatura")) {
                            System.out.println(v1.vehiculos.get(i).getSensores());
                        }
                    }
                }
            } else if (num == 9) {
                int sens = 0;
                int v = 0;
                for (int i = 0; i < v1.vehiculos.size(); i++) {
                    if (v1.vehiculos.get(i).cantidadSensores() >= sens) {
                        v = i;
                        sens = v1.vehiculos.get(i).cantidadSensores();
                    }

                }
                System.out.print(v1.vehiculos.get(v));


            } else if (num == 10) {
                File file = new File("vehiculosficticios.txt");
                try {
                    Scanner input = new Scanner(file);
                    String line;
                    while (input.hasNextLine()) {
                        line = input.nextLine();
                        String[] arrOfLine = line.split(",");
                        for (String element : arrOfLine) {
                            System.out.println(element);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (num == 666) {
                Sensor[] sensores = Sensor.OrdenTemperatura();
                System.out.println();
                for (int i = 0; i < sensores.length; i++) {
                    System.out.println(sensores[i].toString());
                }
            		} 
            
            	else if (num == 777) {
            			
                int instancias = 5;
                System.out.println();
                String source = obtenerCodigoFuente("https://www.carroya.com/buscar/vehiculos/medellin/t4c239.do");

                ArrayList<Integer> ID = new ArrayList<>();
                ArrayList<Long> Modelos = new ArrayList<>();
                ArrayList<String> Marcas = new ArrayList<>();
                ArrayList<Long> Precios = new ArrayList<>();

                int iID = 0;
                int iActualID;
                int iCID;

                int iModelos = 0;
                int iActualModelos;
                int iCModelos;

                int iMarcas = 0;
                int iActualMarcas;
                int iCMarcas;

                int iPrecios = 0;
                int iActualPrecios;
                int iCPrecios;

                String searchID = "data-idvehiculo=\"";
                String searchModelo = "data-ano=\"";
                String searchMarca = "data-brand=\"";
                String searchPrecio = "data-price=\"";

                for(int i = 0; i < instancias; i++){

                    iActualModelos = source.indexOf(searchModelo, iModelos)+ searchModelo.length();
                    iCModelos = source.indexOf("\"", iActualModelos);
                    Modelos.add(Long.parseLong(source.substring(iActualModelos, iCModelos)));
                    iModelos = iActualModelos;

                    iActualMarcas = source.indexOf(searchMarca, iMarcas)+ searchMarca.length();
                    iCMarcas = source.indexOf("\"", iActualMarcas);
                    Marcas.add(source.substring(iActualMarcas, iCMarcas));
                    iMarcas = iActualMarcas;

                    iActualPrecios = source.indexOf(searchPrecio, iPrecios)+ searchPrecio.length();
                    iCPrecios = source.indexOf("\"", iActualPrecios);
                    Precios.add(Long.parseLong(source.substring(iActualPrecios, iCPrecios)));
                    iPrecios = iActualPrecios;

                    iActualID = source.indexOf(searchID, iID)+ searchID.length();
                    iCID = source.indexOf("\"", iActualID);

                    if(ID.contains(Integer.parseInt(source.substring(iActualID, iCID)))){

                        Modelos.remove(Modelos.size()-1);
                        Marcas.remove(Marcas.size()-1);
                        Precios.remove(Precios.size()-1);
                        iID = iActualID;
                        i--;
                    }

                    ID.add(Integer.parseInt(source.substring(iActualID, iCID)));
                    iID = iActualID;
                }

                    int indice = 0;
                    int iActual;
                    int iComilla;


                    for(int i = 0; i < instancias; i++){

                        iActual = source.indexOf(searchModelo, indice)+ searchModelo.length();
                        iComilla = source.indexOf("\"", iActual);
                        Modelos.add(Long.parseLong(source.substring(iActual, iComilla)));
                        indice = iActual;
                    }

                    for(int i = 0; i < instancias; i++){

                        iActual = source.indexOf(searchMarca, indice)+ searchMarca.length();
                        iComilla = source.indexOf("\"", iActual);
                        Marcas.add(source.substring(iActual, iComilla));
                        indice = iActual;
                    }

                    for(int i = 0; i < instancias; i++){

                        iActual = source.indexOf(searchPrecio, indice)+ searchPrecio.length();
                        iComilla = source.indexOf("\"", iActual);
                        Precios.add(Long.parseLong(source.substring(iActual, iComilla)));
                        indice = iActual;
                    }
                    for(int i = 0; i<instancias; i++){

                    Vehiculo v = new Vehiculo(Modelos.get(i).intValue(), Marcas.get(i), Precios.get(i));
                }
            }
        }
    }

    private static String obtenerCodigoFuente(String ruta) throws IOException {

        URL url = new URL(ruta);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String Code = "";
        String lineaAct;

        while((lineaAct = reader.readLine()) != null){

            Code = Code + lineaAct;
        }

        reader.close();
        return Code;
    }
}

