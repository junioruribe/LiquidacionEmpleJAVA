package ejercicios;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Ejercicio3 {

    static ArrayList<Object[]> lista;

    public static void main(String[] args) {
        lista = new ArrayList();
        int opcion;
        String menu = "Seleccione una opcion\n";
        menu += "1. Registrar informacion de empleado\n";
        menu += "2. Mostrar informacion de empleado\n";
        menu += "3. Mostrar informacion por genero\n";
        menu += "4. Eliminar informacion por genero\n";
        menu += "5. actualizar informacion de empleado\n";
        menu += "6. Salir del programa";
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    mostrar();
                    break;
                case 3:
                    mostrarGenero();
                    break;
                case 5:
                    actualizar();
                    break;
            }
        } while (opcion != 6);

    }

    public static void registrar() {
        String nom, doc, edad, gen, ht;
        nom = JOptionPane.showInputDialog("Ingrese Nombre");
        doc = JOptionPane.showInputDialog("Ingrese documento");
        gen = JOptionPane.showInputDialog("Ingrese genero");
        edad = JOptionPane.showInputDialog("Ingrese edad");
        ht = JOptionPane.showInputDialog("Ingrese horas trabajadas");
        Object v[] = {nom, doc, gen, edad, ht};
        lista.add(v);
        mensaje("Trabajador registrado");
    }

    public static void mensaje(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static void mostrar() {
        String ced;
        String gen, edad, nom;
        int HT = 0, VHO, HO, VPHO = 0, HE = 0;
        double VPHEN = 0, VPHED = 0, VPHE, VTP;
        if (lista.size() > 0) {
            ced = JOptionPane.showInputDialog("Ingrese cedula del empleado");
            int pos = getPosicion(ced);
            if (pos == -1) {
                mensaje("No se encontro empleado");
            } else {
                Object v[] = lista.get(pos);
                gen = v[2].toString();
                edad = v[3].toString();
                nom = v[0].toString();
                VHO = getVHO(gen, Integer.parseInt(edad));
                HT = Integer.parseInt(lista.get(pos)[4].toString());
                HO = getHO(HT);
                HE = getHE(HT);
                VPHO = getVPHO(HO, VHO);
                VPHEN = getVPHEN(HE, VHO);
                VPHED = getVPHED(HE, VHO);
                VPHE = VPHEN + VPHED;
                VTP = VPHO + VPHE;
                mensaje("Liquidación de " + nom + 
                        " con cedula " + ced +
                        " edad " + edad + 
                        " y genero " + gen + " es:"
                        + "\nEl valor hora ordinaria (VHO): " + VHO
                        + "\nLa cantidad de horas Ordinarias (HO):" + HO
                        + "\nLa cantidad de horas Extras (HE): " + HE
                        + "\nEl Valor horas ordinarias trabajadas (VPHO): " + VPHO
                        + "\nEl Valor horas extras nocturnas (VPHEN): " + VPHEN
                        + "\nEl Valor horas extras dominicales (VPHED): " + VPHED
                        + "\nEl Valor por horas extras (VPHE): " + VPHE
                        + "\nEl valor total pagado a un trabajador (VTP):" + VTP);
            }
        } else {
            mensaje("Lista vacia");
        }
    }

    public static void mostrarGenero() {
        String gen, out = "";
        if (lista.isEmpty()) {
            mensaje("Lista vacia");
        } else {
            gen = JOptionPane.showInputDialog("Ingrese genero a buscar(Hombre/Muejer)");
            for (Object[] v : lista) {
                if (gen.equalsIgnoreCase(v[2].toString())) {
                    out += "Nombre: " + v[0] + " ";
                    out += "Docuemento: " + v[1] + " ";
                    out += "Genero: " + v[2] + " ";
                    out += "Edad: " + v[3] + " ";
                    out += "Horas T: " + v[4] + "\n";
                }
            }
            mensaje(out);
        }
    }

    public static int getPosicion(String ced) {
        for (int i = 0; i < lista.size(); i++) {
            Object v[];
            v = lista.get(i);
            if (ced.equals(v[1])) {
                return i;
            }
        }
        return -1;
    }

    public static int getVHO(String gen, int edad) {
        int VHO;
        if (gen.equalsIgnoreCase("Hombre") && edad < 18) {
            VHO = 5000;
        } else {
            VHO = 8000;
        }
        if (gen.equalsIgnoreCase("Mujer") && edad < 18) {
            VHO = 7000;
        } else {
            VHO = 10000;
        }
        return VHO;
    }

    public static int getHO(int HT) {
        int HO = 0;
        if (HT <= 80) {
            HO = HT;
        } else {
            HO = 80;
        }
        return HO;
    }

    public static int getHE(int HT) {
        int HE;
        if (HT > 80) {
            HE = HT - 80;
        } else {
            HE = 0;
        }
        return HE;
    }

    public static int getVPHO(int HO, int VHO) {
        int VPHO;
        VPHO = HO * VHO;
        return VPHO;
    }

    public static double getVPHEN(int HE, int VHO) {

        double VPHEN;
        if (HE <= 8) {
            VPHEN = HE * VHO * 1.25;
        } else {
            VPHEN = 8 * VHO * 1.25;

        }
        return VPHEN;
    }

    public static double getVPHED(int HE, int VHO) {

        double VPHED;
        if (HE > 8) {
            VPHED = (HE - 8) * VHO * 1.75;
        } else {
            VPHED = 0;
        }
        return VPHED;
    }
    
    public static void actualizar(){
        String ced,edad,ht;
        if (lista.isEmpty()) {
            mensaje("Lista vacia");
        } else {
           ced = JOptionPane.showInputDialog("Ingrese cedula del empleado");
           int pos = getPosicion(ced);
            if (pos==-1) {
                mensaje("No existe la cedula");
            } else {
                edad = JOptionPane.showInputDialog("Ingrese nueva edad");
                ht = JOptionPane.showInputDialog("Ingrese nueva horas trabajadas");
                Object v[] = lista.get(pos);
                v[3]=edad;
                v[4]=ht;
                lista.set(pos, v);
                mensaje("Empleado actualizado");
            }
        }
    }
}
