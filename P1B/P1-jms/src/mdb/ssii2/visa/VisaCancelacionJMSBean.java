/**
 * Pr&aacute;ctricas de Sistemas Inform&aacute;ticos II
 * VisaCancelacionJMSBean.java
 */

package ssii2.visa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.ActivationConfigProperty;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * @author jaime
 */
@MessageDriven(mappedName = "jms/VisaPagosQueue")
public class VisaCancelacionJMSBean extends DBTester implements MessageListener {
  static final Logger logger = Logger.getLogger("VisaCancelacionJMSBean");
  @Resource
  private MessageDrivenContext mdc;
  
  private static final String UPDATE_PAGO_QRY = "update pago set codRespuesta = 999 where idAutorizacion=?";
  
  private static final String UPDATE_TARJETA_QRY = "update tarjeta as t set saldo = saldo + p.importe " +
		"from pago as p where p.idAutorizacion = ? and p.numeroTarjeta = t.numeroTarjeta";

  private static final String UPDATE_CANCELA_QRY = null;
   // TODO : Definir UPDATE sobre la tabla pagos para poner
   // codRespuesta a 999 dado un código de autorización


  public VisaCancelacionJMSBean() {
  }

  // TODO : Método onMessage de ejemplo
  // Modificarlo para ejecutar el UPDATE definido más arriba,
  // asignando el idAutorizacion a lo recibido por el mensaje
  // Para ello conecte a la BD, prepareStatement() y ejecute correctamente
  // la actualización
  public void onMessage(Message inMessage) {
      TextMessage msg = null;
      Connection con = null;
	  PreparedStatement pstmt = null;
	  String update;
      try {

		  
          if (inMessage instanceof TextMessage) {
              msg = (TextMessage) inMessage;
              logger.info("MESSAGE BEAN: Message received: " + msg.getText());
              int numero = Integer.parseInt(msg.getText());
			  // Crear una conexion u obtenerla del pool
			  con = getConnection();

          // Se busca la ocurrencia de la tarjeta en la tabla

          /* TODO Usar prepared statement si
               isPrepared() == true */
            /**************************************************/
               update  = UPDATE_PAGO_QRY;
               logger.info(update);
               pstmt = con.prepareStatement(update);
               pstmt.setInt(1, numero);
               pstmt.execute();


			   update  = UPDATE_TARJETA_QRY;
               logger.info(update);
               pstmt = con.prepareStatement(update);
               pstmt.setInt(1, numero);
               pstmt.execute();
          } else {
              logger.warning(
                      "Message of wrong type: "
                      + inMessage.getClass().getName());
          }
      } catch (JMSException e) {
          e.printStackTrace();
          mdc.setRollbackOnly();
      } catch (Throwable te) {
          te.printStackTrace();
      }
  }


}
