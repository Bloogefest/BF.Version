/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.version;

import com.bloogefest.annotation.Contract;
import com.bloogefest.annotation.NotNull;
import com.bloogefest.common.validation.NullException;

/**
 * Этот интерфейс содержит методы, применимые к любому формату версий.
 *
 * @since 1.0.0-RC1
 */
public interface Version {

    /**
     * Возвращает истинное значение, если переданная версия новее текущей, в противном случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #same(Version)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    boolean newer(final @NotNull Version version) throws NullException;

    /**
     * Возвращает истинное значение, если переданная версия такая же, как и текущая, в противном случае — ложное
     * значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(Version)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    boolean same(final @NotNull Version version) throws NullException;

    /**
     * Возвращает истинное значение, если переданная версия старее текущей, в противном случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(Version)
     * @see #same(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    boolean older(final @NotNull Version version) throws NullException;

}
