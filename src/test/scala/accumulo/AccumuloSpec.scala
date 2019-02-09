package accumulo

import com.google.common.io.Files
import org.apache.accumulo.minicluster.{MiniAccumuloCluster, MiniAccumuloConfig}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec}

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
      1 must be(1)
    }
  }

}
