package app.view

import app.model.ElevatorStatus

import scala.io.StdIn

object ViewerReader {

  def option():Int={
    println("Choose option from :")
    println("1. Pickup ( current floor and direction (minus goes up is plus go up 0 in current code is no handled))")
    println("2. Update override values of chosen " +
      "elevator it can even teleport elevator to different floor !(id of elevator , new floor of elevator, new target " +
      "floor only accept  one value)")
    println("3. Step in simulation")
    println("4. Add new floor to elevator(id of elevator , destiny floor)")
    println("5. Status of all elevators")
    println("6+. Exit")
      StdIn.readInt()
  }
  def pickupPrint():Unit = println("PickUp")
  def updatePrint():Unit = println("Update")
  def stepPrint():Unit = println("Step in Simulation")
  def addPrint():Unit = println("Add")
  def statusPrint(list: List[(String, Int, String, ElevatorStatus.ElevatorStatus)]):Unit =
    list.foreach(println(_))
  def exitPrint():Unit = println("Status")



}
