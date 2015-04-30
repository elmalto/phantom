package com.websudos.phantom.builder.serializers

import com.datastax.driver.core.ConsistencyLevel
import com.websudos.phantom.builder.query.QueryBuilderTest
import com.websudos.phantom.tables.Recipes
import com.websudos.phantom.dsl._
import com.websudos.util.testing._

class UpdateQuerySerializationTest extends QueryBuilderTest {

  "An Update query should" - {
    "allow specifying consistency levels" - {
      "specify a consistency level of ALL in an AssignmentsQuery" in {

        val url = gen[String]

        val query = Recipes.update()
          .where(_.url eqs url)
          .modify(_.servings setTo Some(5))
          .consistencyLevel_=(ConsistencyLevel.ALL)
          .queryString

        query shouldEqual s"UPDATE phantom.Recipes USING CONSISTENCY ALL SET servings = 5 WHERE url = '$url'"

      }

      "specify a consistency level in a ConditionUpdateQuery" in {
        val url = gen[String]

        val query = Recipes.update()
          .where(_.url eqs url)
          .modify(_.servings setTo Some(5))
          .onlyIf(_.description is Some("test"))
          .consistencyLevel_=(ConsistencyLevel.ALL)
          .queryString

        query shouldEqual s"UPDATE phantom.Recipes USING CONSISTENCY ALL SET servings = 5 WHERE url = '$url' IF description = 'test'"

      }
    }
  }

}
