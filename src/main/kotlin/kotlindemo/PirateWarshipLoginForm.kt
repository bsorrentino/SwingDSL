package kotlindemo

import dsl.*
import org.jdesktop.beansbinding.BeanProperty
import java.awt.Color
import java.awt.EventQueue.invokeLater
import java.lang.Exception
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.border.LineBorder
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy
import org.jdesktop.beansbinding.AutoBinding
import org.jdesktop.beansbinding.Bindings


data class Credentials( var user:String = "", var password:String = "" )


class PirateWarshipLoginForm : JFrame() {


    val credentials = Credentials( user = "user" );

    var user = BeanProperty.create<Credentials,String>("user");
    var password = BeanProperty.create<Credentials,String>("password");

    private val backgroundImage by lazy {

        ImageIcon( resource<PirateWarshipLoginForm>("PirateShip.gif") )
    }

    private fun validateUser(username: String, password: String): Boolean {
        return username == "user" && password == "user"
    }


    /**
     * Create the frame.
     */
    init {

        frame(title = "Pirate Warships") {
            defaultCloseOperation = EXIT_ON_CLOSE
            bounds = rectangle(dimension = 640 x 400)
            isResizable = false
            isVisible = true
            setLocationRelativeTo(null)

            panel {
                bounds = rectangle(dimension = 640 x 400)
                border = EmptyBorder(5, 5, 5, 5)
                layout = null

                button("Login") {
                    border = LineBorder(Color(0, 0, 255), 1, true)
                    setBounds(10, 211, 115, 23)
                    addActionListener {
                        tryToLogin()
                    }
                }

                button("Registration") {
                    border = LineBorder(Color(0, 0, 255), 1, true)
                    setBounds(10, 247, 115, 23)
                    addActionListener {
                        JOptionPane.showMessageDialog(this@panel, "Registration screen not available.", "Information", JOptionPane.INFORMATION_MESSAGE)
                        Thread.interrupted()
                    }
                }

                val usernameTextField = textfield( source = credentials, property = user ) {
                    font = boldFont("Arial Black", 11)
                    isOpaque = false
                    border = LineBorder(Color(0, 0, 0), 1, true)
                    bounds = rectangle(10, 91, 115 x 20)
                    columns = 10
                }


                label("User") {
                    font = boldFont("Ariel")
                    text = blackText("User:")
                    bounds = rectangle(10, 66, 46 x 14)
                }

                val passwordTextField = passwordfield( source = credentials, property = password ) {
                    font = boldFont("Arial Black", 11)
                    isOpaque = false
                    border = LineBorder(Color(0, 0, 0), 1, true)
                    bounds = rectangle(10, 137, 115 x 20)
                    columns = 10

                }

                label("Password") {
                    font = boldFont("Ariel")
                    text = blackText("Password:")
                    bounds = rectangle(10, 113, 80 x 23)
                }

                label {
                    horizontalTextPosition = SwingConstants.CENTER
                    horizontalAlignment = SwingConstants.CENTER
                    bounds = rectangle(dimension = 640 x 400)
                    icon = backgroundImage
                }

            }
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            tryWith {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            }

            invokeLater {

                val form = PirateWarshipLoginForm()

            }
        }

        private inline fun tryWith(function: () -> Unit) {
            try {
                function()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    private fun JPanel.tryToLogin() {
        val username = credentials.user
        val password = credentials.password

        if (validateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful.", "Information", JOptionPane.INFORMATION_MESSAGE)
            System.exit(0)
        } else {
            JOptionPane.showMessageDialog(this, "Wrong username or password.", "Error", JOptionPane.ERROR_MESSAGE)
            Thread.interrupted()
        }
    }
}


