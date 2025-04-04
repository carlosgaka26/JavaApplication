package javasystemapplication;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class GeneradorPDFAltaProducto {

    public static void generarPDF(String cliente, String almacen, List<String[]> productos, String observaciones) {
        try {
            String fecha = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String nombreArchivo = "alta_" + cliente.replaceAll("\\s+", "_") + "_" + fecha + ".pdf";

            File carpeta = new File("altas_generadas");
            if (!carpeta.exists()) carpeta.mkdirs();

            String ruta = carpeta.getAbsolutePath() + File.separator + nombreArchivo;

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();

            // Título
            var fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            doc.add(new Paragraph("ENTRADA DE ALMACÉN", fontTitulo));
            doc.add(new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())));
            doc.add(new Paragraph("Cliente: " + cliente));
            doc.add(new Paragraph("Almacén: " + almacen));
            doc.add(new Paragraph(" "));

            // 📌 Verificar si la lista de productos está vacía
            if (productos.isEmpty()) {
                doc.add(new Paragraph("⚠ No hay productos registrados en esta alta."));
            } else {
                // Tabla de productos
                PdfPTable table = new PdfPTable(5);
                table.setWidths(new float[]{3, 2, 1, 2, 2});
                table.setWidthPercentage(100);

                String[] encabezados = {"Producto", "Unidad", "Cantidad", "Presentación", "Lote"};
                for (String col : encabezados) {
                    PdfPCell celda = new PdfPCell(new Phrase(col, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(celda);
                }

                // 🔍 Depuración: Verificar si estamos añadiendo productos
                System.out.println("=== GENERANDO TABLA PDF ===");

                for (String[] fila : productos) {
                    for (String campo : fila) {
                        table.addCell(new Phrase(campo));

                        // 🔍 Imprimir en consola cada celda
                        System.out.print(campo + " | ");
                    }
                    System.out.println();
                }

                doc.add(table);
            }

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Observaciones: " + (observaciones == null || observaciones.isEmpty() ? "Ninguna" : observaciones)));

            doc.close();

            JOptionPane.showMessageDialog(null, "PDF generado correctamente en:\n" + ruta, "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Abrir el PDF automáticamente
            Desktop.getDesktop().open(new File(ruta));

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
