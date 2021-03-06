/*
 * Copyright 2013-2015 Websudos, Limited.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * - Explicit consent must be obtained from the copyright owner, Outworkers Limited before any redistribution is made.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.websudos.phantom.jdk8

import java.time.{LocalDate, OffsetDateTime, ZoneOffset, ZonedDateTime}

import com.outworkers.util.testing.{Sample, _}

package object tables {

  implicit object Jdk8RowSampler extends Sample[Jdk8Row] {
    def sample: Jdk8Row = {
      Jdk8Row(
        gen[String],
        OffsetDateTime.now(ZoneOffset.UTC).plusSeconds(gen[Long]),
        ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(gen[Long]),
        LocalDate.now().plusDays(gen[Long])
      )
    }
  }

  implicit object OptionalJdk8RowSampler extends Sample[OptionalJdk8Row] {
    def sample: OptionalJdk8Row = {
      OptionalJdk8Row(
        gen[String],
        Some(OffsetDateTime.now(ZoneOffset.UTC).plusSeconds(gen[Long])),
        Some(ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(gen[Long])),
        Some(LocalDate.now().plusDays(gen[Long]))
      )
    }
  }

}
