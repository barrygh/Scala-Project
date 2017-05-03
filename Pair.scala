import java.io.Serializable

import scala.beans.{BeanProperty, BooleanBeanProperty}

class Pair[V, T](@BeanProperty var first: V, @BeanProperty var second: T)
	extends Serializable
