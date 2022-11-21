package app.model

import app.model.ElevatorStatus.{ElevatorStatus, up}

import scala.annotation.tailrec
import scala.collection.immutable.List

case class Elevator(id: String, currentFloor: Int, targetFloorList: Seq[Int], elevatorStatus: ElevatorStatus) {

  def stepThisElevator: Elevator = {
    lazy val elevatorStatus = upOrDown(currentFloor, (targetFloorList))
    lazy val newCurrentFloor = elevatorStatus match {
      case ElevatorStatus.up => (x: Int) => x + 1
      case ElevatorStatus.down => (x: Int) => x - 1
    }
    lazy val newTargetFloorList = targetFloorList.filter(x => x != currentFloor)
    elevatorStatus match {
      case _ if targetFloorList.isEmpty =>Elevator(id, currentFloor, targetFloorList, ElevatorStatus.stop)
      case ElevatorStatus.stop =>
        Elevator(id, currentFloor, targetFloorList.tail, ElevatorStatus.stop)
      case _ if newTargetFloorList.length == targetFloorList.length =>
        Elevator(id, newCurrentFloor(currentFloor), targetFloorList, elevatorStatus)
      case _ =>
        Elevator(id, currentFloor, targetFloorList.filter(x => x != currentFloor), ElevatorStatus.stop)
    }
  }

  def status = (id, currentFloor, targetFloorList.toString(), elevatorStatus)

  def addFloor(newFloor: Int): Elevator =
    Elevator(id, currentFloor, targetFloorList.appended(newFloor), elevatorStatus)

  def updateTopFloorAndCurrentFloor(currentFloor:Int,newFloor: Int): Elevator =
    Elevator(id, currentFloor, targetFloorList.tail.prepended(newFloor), elevatorStatus)

  def updateTopFloor( newFloor: Int): Elevator =
    Elevator(id, currentFloor, targetFloorList.tail.prepended(newFloor), elevatorStatus)


  def upOrDown(currentFloor: Int, targetFloorList:Seq[Int]): ElevatorStatus = {
    targetFloorList.isEmpty match {
      case  true => ElevatorStatus.stop
      case _ if targetFloorList.head > currentFloor => ElevatorStatus.up
      case _ if targetFloorList.head < currentFloor => ElevatorStatus.down
      case _ => ElevatorStatus.stop
    }
  }


}
