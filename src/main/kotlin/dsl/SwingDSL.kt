package dsl

import org.jdesktop.beansbinding.AutoBinding
import org.jdesktop.beansbinding.BeanProperty
import org.jdesktop.beansbinding.Bindings
import java.awt.Dimension
import java.awt.Font
import java.awt.Rectangle
import java.net.URL
import javax.swing.*

typealias FontSize = Int
typealias Width = Int
typealias Height = Int
typealias CoordinateX = Int
typealias CoordinateY = Int

/**
 * JFrame
 */
inline fun JFrame.frame(title: String = "", init: JFrame.() -> Unit): JFrame = apply {
    this.title = title
    init()
}

/**
 * JPanel
 */
inline fun JFrame.panel(init: JPanel.() -> Unit): JPanel = JPanel().apply {
    init()
}.also { contentPane = it }

/**
 * JLabel
 */
inline fun JPanel.label(label: String = "", init: JLabel.() -> Unit): JLabel = JLabel(label).apply {
    init()
}.also { add(it) }

/**
 * JButton
 */
inline fun JPanel.button(label: String = "", init: JButton.() -> Unit): JButton = JButton(label).apply {
    init()
}.also { add(it) }

/**
 * JTextField
 */
inline fun JPanel.textfield(label: String = "", init: JTextField.() -> Unit): JTextField = JTextField(label).apply {
    init()
}.also { add(it) }

inline fun <S> JPanel.textfield(label: String = "", source:S, property:BeanProperty<S,String>, init: JTextField.() -> Unit): JTextField = JTextField(label).apply {
    init()
    val binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
            source,
            property,
            this,
            BeanProperty.create<JTextField,String>("text"))
    binding.bind();


}.also { add(it) }

/**
 * JPasswordField
 */
inline fun JPanel.passwordfield(label: String = "", init: JPasswordField.() -> Unit): JTextField = JPasswordField(label).apply {
    init()
}.also { add(it) }

inline fun <S> JPanel.passwordfield(label: String = "", source:S, property:BeanProperty<S,String>, init: JPasswordField.() -> Unit): JPasswordField = JPasswordField(label).apply {
    init()
    val binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
            source,
            property,
            this,
            BeanProperty.create<JPasswordField,String>("text"))
    binding.bind();

}.also { add(it) }

/**
 *
 */
inline fun <reified T> resource(resourceDestination: String): URL {
    return T::class.javaClass.classLoader.getResource(resourceDestination);
    //return T::class.java.getResource(resourceDestination)
}

/**
 * Rectangle
 */
fun rectangle(x: CoordinateX = 0, y: CoordinateY = 0, dimension: Dimension): Rectangle = Rectangle(x, y, dimension.width, dimension.height)

/**
 *  width x height
 */
infix fun Width.x(height: Height) = Dimension(this, height)


fun blackText(text: String) = "<html><font color='black'>$text</font></html>"

fun boldFont(font: String, size: FontSize = 14) = Font(font, Font.BOLD, size)