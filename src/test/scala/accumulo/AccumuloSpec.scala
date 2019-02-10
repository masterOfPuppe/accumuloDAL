package accumulo

import com.google.common.io.Files
import org.apache.accumulo.core.data.Mutation
import org.apache.accumulo.minicluster.{MiniAccumuloCluster, MiniAccumuloConfig}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec}
import utils.Utils

class AccumuloSpec extends WordSpec with MustMatchers with BeforeAndAfterAll{

  var accumuloMini : MiniAccumuloCluster= _
  val user = "user"
  val password = "password"

  override protected def beforeAll(): Unit = {
    super.beforeAll()

    val miniConfig = new MiniAccumuloConfig(Files.createTempDir(), password)
    accumuloMini = new MiniAccumuloCluster(miniConfig)
    accumuloMini.start()

  }

  override protected def afterAll(): Unit = {
    accumuloMini.stop()
    super.afterAll()
  }

  "this test" must {
    "pass" in {
      Utils.fillAccumuloTable(null, "data.txt")
      1 must be(1)
    }
  }

}
