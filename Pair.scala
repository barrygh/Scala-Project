package kNearestNeighbors

import java.io.Serializable
import scala.beans.BeanProperty

class Pair[V, T](@BeanProperty var first: V, @BeanProperty var second: T)
    extends Serializable
