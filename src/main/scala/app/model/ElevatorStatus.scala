package app.model

object ElevatorStatus extends Enumeration {
  type ElevatorStatus = Value
  val stop = Value("Stopping")
  val up = Value("Going_Up")
  val down  = Value("Going_Down")
}
