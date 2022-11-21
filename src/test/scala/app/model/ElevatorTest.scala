package app.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class ElevatorTest extends AnyFlatSpec with Matchers{

val testElevatorUp = Elevator("2",1,Seq(5,2),ElevatorStatus.stop)
val testElevatorDownUp = Elevator("2",4,Seq(3,5),ElevatorStatus.stop)
val testElevatorEmpty = Elevator("2",8,Seq(),ElevatorStatus.stop)


  "addFloor" should "add new floor" in {


    assert(testElevatorUp.addFloor(9).targetFloorList.size==3)
  }

  "addFloor" should "add new floor on end" in {
    assert(testElevatorUp.addFloor(9).targetFloorList(2) == 9)
  }


//  it should "updateTopFloorAndCurrentFloor" in {
//
//  }
//
//  it should "updateTopFloor" in{
//
//  }
  "upOrDown" should "up" in{
    assert(testElevatorUp.upOrDown(1,Seq(6)) == ElevatorStatus.up)
  }


  "upOrDown" should "down" in {
    assert(testElevatorUp.upOrDown(3, Seq(1)) == ElevatorStatus.down)
  }

  "stepThisElevator" should "stay if target is empty" in {
    assert(testElevatorEmpty.stepThisElevator.currentFloor == 8 )
  }

  "stepThisElevator" should "change current to 2" in {

    assert(testElevatorUp.stepThisElevator.currentFloor==2)
  }
  "stepThisElevator" should "remove 2 floor form target" in {

    val test = testElevatorUp.stepThisElevator.stepThisElevator
    assert(test.targetFloorList.size == 1)
    assert(test.currentFloor == 2)
    assert(test.targetFloorList(0)== 5)
    assert(test.elevatorStatus== ElevatorStatus.stop)
  }

  "stepThisElevator" should "finish up" in {

    val test = testElevatorUp.stepThisElevator.stepThisElevator.stepThisElevator.stepThisElevator.stepThisElevator.stepThisElevator
    assert(test.targetFloorList.size == 0)
    assert(test.currentFloor == 5)
    assert(test.elevatorStatus == ElevatorStatus.stop)
  }

  "stepThisElevator" should "remove 3 floor form target" in {

    val test = testElevatorDownUp.stepThisElevator.stepThisElevator
    assert(test.targetFloorList.size == 1)
    assert(test.currentFloor == 3)
    assert(test.targetFloorList(0) == 5)
    assert(test.elevatorStatus == ElevatorStatus.stop)
  }

  "stepThisElevator" should "finish down up" in {

    val test = testElevatorDownUp.stepThisElevator.stepThisElevator.stepThisElevator.stepThisElevator.stepThisElevator.stepThisElevator
    assert(test.targetFloorList.size == 0)
    assert(test.currentFloor == 5)
    assert(test.elevatorStatus == ElevatorStatus.stop)
  }


}
