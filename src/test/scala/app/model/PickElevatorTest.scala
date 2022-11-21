package app.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class PickElevatorTest extends AnyFlatSpec with Matchers{


  val elevatorEngineTest = ElevatorEngine(2)
    .updateElevatorsEngine(0,6,5)
    .addFloorToElevatorToElevatorsEngine(0,4)
    .updateElevatorsEngine(1,4,1)
    .addFloorToElevatorToElevatorsEngine(1,2)
  "id" should " be in range" in {
    val test = elevatorEngineTest.findBestElevatorSim(8,-1)
    assert(test == 0)
  }


}
