package scalapasswordhash

import org.scalacheck.Gen
import org.scalacheck.Gen._
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import passwordhash.{PasswordHash => JPasswordHash}

class PasswordHashTest extends FunSuite with GeneratorDrivenPropertyChecks  {

  test("hash generated from Java should validate with Scala") {
    forAll (identifier) { (password: String) =>
      val h1 = JPasswordHash.createHash(password)
      assert(PasswordHash.validatePassword(password, h1))
    }
  }

  test("hash generated from Scala should validate with Java") {
    forAll (identifier) { (password: String) =>
      val h1 = PasswordHash.createHash(password)
      assert(JPasswordHash.validatePassword(password, h1))
    }
  }

}
