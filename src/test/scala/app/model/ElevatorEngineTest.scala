package app.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class ElevatorEngineTest extends AnyFlatSpec with Matchers {

  val elevatorEngineTest = ElevatorEngine(4).
    addFloorToElevatorToElevatorsEngine(0, 6).
    addFloorToElevatorToElevatorsEngine(0, 2).
    addFloorToElevatorToElevatorsEngine(1, 1).
    addFloorToElevatorToElevatorsEngine(1, 1).
    updateElevatorsEngine(2, 10, 6).
    updateElevatorsEngine(3, 5, 18)

  "id" should " be in range" in {
    val test = elevatorEngineTest.elevatorEngineList().map(x => x.id.toInt)

    assert(test.nonEmpty)
    assert(test.size == 4)
    assert(test.equals(List(0, 1, 2, 3)))
  }
  "engine" should "specyfic" in {
    val test = elevatorEngineTest.stepElevatorsEngine()
      .stepElevatorsEngine()
      .stepElevatorsEngine()

    val testList = test.elevatorEngineList()
    assert(testList(0).equals(Elevator("0",2,List(6),ElevatorStatus.stop)))
    assert(testList(1).equals(Elevator("1",1,List(),ElevatorStatus.stop)))
    assert(testList(2).equals(Elevator("2",7,List(6),ElevatorStatus.down)))
    assert(testList(3).equals(Elevator("3",8,List(18),ElevatorStatus.up)))

  }

}
