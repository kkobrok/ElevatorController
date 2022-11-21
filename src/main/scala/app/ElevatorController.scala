package app

import app.model.{Elevator, ElevatorEngine}
import app.view.ViewerReader

import java.lang.annotation.Target
import scala.annotation.tailrec

trait ElevatorController {

  import scala.io.StdIn._
  import ViewerReader._

  def run(): Unit = {
    var inputVal = option()
    while ((1 until (6)).contains(inputVal)) {
      inputVal match {
        case 1 => elevatorEngine = pickup(readInt(), readInt())
        case 2 => elevatorEngine = update(readInt(), readInt(), readInt())
        case 3 => elevatorEngine = step()
        case 4 => elevatorEngine = addFloor(readInt(), readInt())
        case 5 => status()
      }
      inputVal = option()
    }
    exitPrint()
  }

  val numberOfElevator = 16
  var elevatorEngine = ElevatorEngine(numberOfElevator)

  // takes as prority elevator that goes fastest and if the elevator is moving in same direction on stopping
  def pickup(currentFloor: Int, direction: Int): ElevatorEngine = {
    pickupPrint()
    elevatorEngine.addFloorToElevatorToElevatorsEngine(
      elevatorEngine.findBestElevatorSim(currentFloor, direction), currentFloor)
  }

  def addFloor(id: Int, destinationFloor: Int): ElevatorEngine = {
    addPrint()
    elevatorEngine.addFloorToElevatorToElevatorsEngine(id, destinationFloor)
  }

  def step(): ElevatorEngine = {
    stepPrint()
    elevatorEngine.stepElevatorsEngine()
  }
  def update(id: Int, newCurrentFloor: Int, target: Int): ElevatorEngine
  = {
    updatePrint()
    elevatorEngine.updateElevatorsEngine(id, newCurrentFloor, target)
  }

  def status() = statusPrint(statusRaw())
  def statusRaw() = elevatorEngine.elevatorEngineList().map(x => x.status)




  //
  //  def status():[(Int, Int, Int)
  //  , (Int, Int, Int)
  //  ,...]

}
