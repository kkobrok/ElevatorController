package app.model

import scala.annotation.tailrec

class ElevatorEngine(numberOfElevators: Int, elevatorEngineList: List[Elevator]) {


  def elevatorEngineList(): List[Elevator] ={
    elevatorEngineList
  }

  private def stepElevators(): List[Elevator] = {
    for {
      x <- elevatorEngineList
    } yield x.stepThisElevator
  }

  private def updateElevator(id: Int, currentFloor: Int, targetFloor: Int): List[Elevator] = {
    elevatorEngineList.updated(id,Elevator(id.toString,currentFloor,Seq(targetFloor),ElevatorStatus.stop))
  }

  private def addFloorToElevator(id:Int,targetFloor: Int) :List[Elevator] = {
    elevatorEngineList.updated(id,elevatorEngineList(id).addFloor(targetFloor))
  }


  def stepElevatorsEngine(): ElevatorEngine = {
    ElevatorEngine(numberOfElevators, stepElevators())
  }

  def addFloorToElevatorToElevatorsEngine(id:Int,targetFloor: Int):ElevatorEngine={
    ElevatorEngine(numberOfElevators, addFloorToElevator(id,targetFloor))
  }

  def updateElevatorsEngine(id: Int, currentFloor: Int, targetFloor: Int): ElevatorEngine = {
    ElevatorEngine(numberOfElevators, updateElevator(id, currentFloor, targetFloor))
  }

  def findBestElevatorSim(destination: Int, direction: Int): Int = {
    findBestRecursion(destination, for {
      x <- if (direction>0){
        elevatorEngineList().filter(x=>x.elevatorStatus != (ElevatorStatus.down))
      }else {
        elevatorEngineList().filter(x=>x.elevatorStatus  != (ElevatorStatus.up))
      }
    } yield x.addFloor(destination))
  }

  @tailrec
  private def findBestRecursion(destination: Int, elevatorList: List[Elevator]): Int = {
    elevatorList.filter(x => x.currentFloor == destination).length match {
      case 0 => findBestRecursion(destination, for {
        x <- elevatorList
      } yield x.stepThisElevator)
      case _ => elevatorList.filter(x =>x.currentFloor == destination).head.id.toInt
    }
  }


}

object ElevatorEngine {
  def apply(numberOfElevators: Int): ElevatorEngine =
    new ElevatorEngine(numberOfElevators, emptyElevatorFiller(numberOfElevators))

  private def apply(numberOfElevators: Int, elevatorEngineList: List[Elevator]): ElevatorEngine =
    new ElevatorEngine(numberOfElevators, elevatorEngineList)

  private def emptyElevatorFiller(numberOfElevators: Int) = (for {
    x <- 0 until numberOfElevators
  } yield Elevator(x.toString, 0, Seq.empty, ElevatorStatus.stop)).toList
}
