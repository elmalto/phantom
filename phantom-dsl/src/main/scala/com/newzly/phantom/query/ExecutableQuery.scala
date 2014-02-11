/*
 * Copyright 2013 newzly ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.newzly.phantom.query

import scala.concurrent.{ Future => ScalaFuture }

import com.datastax.driver.core.{ Row, ResultSet, Session, Statement }
import com.newzly.phantom.{ CassandraResultSetOperations, CassandraTable }

trait ExecutableStatement extends CassandraResultSetOperations {

  def qb: Statement

  def future()(implicit session: Session): ScalaFuture[ResultSet] = {
    scalaStatementToFuture(qb)
  }
}

trait ExecutableQuery[T <: CassandraTable[T, _], R] extends CassandraResultSetOperations {

  def qb: Statement
  def table: CassandraTable[T, _]
  def fromRow(r: Row): R

  def future()(implicit session: Session): ScalaFuture[ResultSet] = {
    scalaStatementToFuture(qb)
  }
}