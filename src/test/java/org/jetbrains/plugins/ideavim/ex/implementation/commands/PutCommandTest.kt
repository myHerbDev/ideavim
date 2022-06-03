/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2022 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.jetbrains.plugins.ideavim.ex.implementation.commands

import com.maddyhome.idea.vim.api.injector
import org.jetbrains.plugins.ideavim.VimTestCase

/**
 * @author Alex Plate
 */
class PutCommandTest : VimTestCase() {
  // VIM-550 |:put|
  fun `test put creates new line`() {
    configureByText("Test\n" + "Hello <caret>World!\n")
    typeText(injector.parser.parseKeys("\"ayw"))
    typeText(commandToKeys("put a"))
    assertState(
      "Test\n" +
        "Hello World!\n" +
        "<caret>World\n"
    )
  }

  // VIM-551 |:put|
  fun `test put default`() {
    configureByText("<caret>Hello World!\n")
    typeText(injector.parser.parseKeys("yw"))
    typeText(commandToKeys("put"))
    assertState("Hello World!\n" + "<caret>Hello \n")
  }
}
