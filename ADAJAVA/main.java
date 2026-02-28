import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class main extends JFrame {
    private JComboBox<String> comboTamanos;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;
    private PanelGrafica panelVisual;
    private JButton btnOrdenamiento, btnBusqueda, btnLimpiar;
    private JTextArea txtConsola;

    public main() {
        setTitle("Comparador de Algoritmos");
        setSize(1100, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSup = new JPanel(new FlowLayout());
        panelSup.setBackground(new Color(44, 62, 80));
        String[] opts = {"10 (Fijo)", "10", "100", "1000", "10000", "100000", "1000000"};
        comboTamanos = new JComboBox<>(opts);
        btnOrdenamiento = new JButton("Ordenar");
        btnBusqueda = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar Gráfica");

        panelSup.add(comboTamanos); panelSup.add(btnOrdenamiento); panelSup.add(btnBusqueda); panelSup.add(btnLimpiar);
        add(panelSup, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Algoritmo", "Tamaño", "Tiempo (ms)", "Memoria (MB)"}, 0);
        tablaResultados = new JTable(modeloTabla);
        add(new JScrollPane(tablaResultados) {{ setPreferredSize(new Dimension(300, 0)); }}, BorderLayout.WEST);

        panelVisual = new PanelGrafica();
        panelVisual.setBackground(Color.WHITE);
        txtConsola = new JTextArea() {{ setEditable(false); setBackground(Color.BLACK); setForeground(new Color(50, 255, 50)); }};
        
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelVisual, new JScrollPane(txtConsola));
        split.setDividerLocation(450);
        add(split, BorderLayout.CENTER);

        btnOrdenamiento.addActionListener(e -> ejecutar(true));
        btnBusqueda.addActionListener(e -> ejecutar(false));
        btnLimpiar.addActionListener(e -> { panelVisual.limpiar(); modeloTabla.setRowCount(0); });
    }

    private void ejecutar(boolean esOrd) {
        String sel = comboTamanos.getSelectedItem().toString();
        int n = sel.equals("10 (Fijo)") ? 10 : Integer.parseInt(sel);
        int[] original = sel.equals("10 (Fijo)") ? new int[]{42, 7, 89, 15, 63, 2, 31, 55, 98, 12} : Algoritmos.generarArreglo(n);
        
        txtConsola.append("\nPrueba iniciada: " + sel + "\n");
        
        new Thread(() -> {
            String[] noms = esOrd ? new String[]{"Burbuja", "Mezcla", "QuickSort"} : new String[]{"Lineal", "Binaria", "Jump"};
            for (int i = 0; i < 3; i++) {
                int[] copia = Arrays.copyOf(original, original.length);
                long inicio = System.currentTimeMillis();
                int[] res;
                long tiempo = 0;

                if (esOrd) {
                    if (i == 0) { if (n <= 30000) res = Algoritmos.ordenamiento_burbuja(copia); else { res = copia; tiempo = -1; } }
                    else if (i == 1) res = Algoritmos.ord_mezcla(copia);
                    else res = Algoritmos.ord_quick(copia);
                } else {
                    Arrays.sort(copia); res = copia;
                    if (i == 0) Algoritmos.buscar_lineal(copia, original[0]);
                    else if (i == 1) Algoritmos.busqueda_binaria(copia, original[0]);
                    else Algoritmos.busqueda_jump(copia, original[0]);
                }
                
                if (tiempo != -1) tiempo = System.currentTimeMillis() - inicio;
                final long fT = tiempo; final String fN = noms[i]; final int[] arrRes = res;

                SwingUtilities.invokeLater(() -> {
                    if (fT != -1) {
                        panelVisual.agregarPunto(fN, n, fT);
                        modeloTabla.addRow(new Object[]{fN, sel, fT, "1"});
                    }
                    if (n <= 20) txtConsola.append("[" + fN + "] Ordenado: " + Arrays.toString(arrRes) + "\n");
                });
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new main().setVisible(true));
    }
}