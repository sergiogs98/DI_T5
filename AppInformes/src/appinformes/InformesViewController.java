/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import static appinformes.AppInformes.conexion;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author sergy
 */
public class InformesViewController implements Initializable {

    @FXML
    private MenuItem m_facturas;
    @FXML
    private MenuItem m_ventastotales;
    @FXML
    private MenuItem m_facturascliente;
    @FXML
    private MenuItem m_subinforme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void onAction(ActionEvent event) {
        generaInforme("facturas.jasper");
    }

    @FXML
    private void onAction1(ActionEvent event) {
        generaInforme("Ventas Totales.jasper");
    }

    @FXML
    private void onAction2(ActionEvent event) {
        String param = JOptionPane.showInputDialog("Introduce id del cliente");
        generaInforme("Facturas_Cliente.jasper" , param);
    }

    @FXML
    private void onAction3(ActionEvent event) {
    }
    
    public void generaInforme(String informe) {

        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource(informe));
            //Map de parámetros
            Map parametros = new HashMap();
            //int nproducto = Integer.valueOf(tintro.getText());
            //parametros.put("ParamProducto", nproducto);

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void generaInforme(String informe, String param) {

        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource(informe));
            //Map de parámetros
            Map parametros = new HashMap();
            parametros.put("addressid", Integer.parseInt(param));

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
