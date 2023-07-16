/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.version;

import com.bloogefest.annotation.Contract;
import com.bloogefest.annotation.NotNull;
import com.bloogefest.annotation.Nullable;
import com.bloogefest.annotation.Range;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

/**
 * Этот интерфейс содержит методы, применимые к одиночному формату версий.
 *
 * @see Impl
 * @see #of(int)
 * @since 1.0.0-RC1
 */
public interface SingleVersion extends Version {

    /**
     * Создаёт и возвращает
     * {@linkplain Impl#Impl(int) встроенную реализацию на основе переданного главного номера версии}.
     *
     * @param major главный номер версии.
     *
     * @return {@linkplain Impl#Impl(int) Встроенную реализацию на основе переданного главного номера версии}.
     *
     * @see Impl
     * @see Impl#Impl(int)
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    static @NotNull SingleVersion of(final @Range(from = 0, to = Integer.MAX_VALUE) int major) {
        return new Impl(major);
    }

    /**
     * Возвращает главный номер версии.
     *
     * @return Главный номер версии.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    @Range(from = 0, to = Integer.MAX_VALUE) int major();

    /**
     * Возвращает истинное значение, если переданная версия одиночного формата новее текущей, в противном случае —
     * ложное значение.
     *
     * @param version версия одиночного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean newer(final @NotNull SingleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version").major() > major();
    }

    /**
     * Если формат переданной версии является одиночным и если переданная версия новее текущей, то возвращает истинное
     * значение, в противном случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(SingleVersion)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> _; _ -> fail")
    default boolean newer(final @NotNull Version version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof SingleVersion version_ && newer(version_);
    }

    /**
     * Возвращает истинное значение, если переданная версия одиночного формата такая же, как и текущая, в противном
     * случае — ложное значение.
     *
     * @param version версия одиночного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(Version)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean same(final @NotNull SingleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version").major() == major();
    }

    /**
     * Возвращает истинное значение, если переданная версия такая же, как и текущая и её формат является одиночным, в
     * противном случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> _; _ -> fail")
    default boolean same(final @NotNull Version version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof SingleVersion version_ && same(version_);
    }

    /**
     * Возвращает истинное значение, если переданная версия одиночного формата старее текущей, в противном случае —
     * ложное значение.
     *
     * @param version версия одиночного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean older(final @NotNull SingleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version").major() < major();
    }

    /**
     * Возвращает истинное значение, если переданная версия старее текущей и её формат является одиночным, в противном
     * случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(SingleVersion)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> _; _ -> fail")
    default boolean older(final @NotNull Version version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof SingleVersion version_ && older(version_);
    }

    /**
     * Встроенная реализация.
     *
     * @see SingleVersion
     * @see #Impl(int)
     * @since 1.0.0-RC1
     */
    class Impl implements SingleVersion {

        /**
         * Главный номер версии.
         *
         * @since 1.0.0-RC1
         */
        protected final @Range(from = 0, to = Integer.MAX_VALUE) int major;

        /**
         * Создаёт встроенную реализацию на основе переданного главного номера версии.
         *
         * @param major главный номер версии.
         *
         * @since 1.0.0-RC1
         */
        public Impl(@Range(from = 0, to = Integer.MAX_VALUE) final int major) {
            this.major = major;
        }

        /**
         * Возвращает {@linkplain #major главный номер версии}.
         *
         * @return {@linkplain #major Главный номер версии}.
         *
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("-> const")
        public @Range(from = 0, to = Integer.MAX_VALUE) int major() {
            return major;
        }

        /**
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("!null -> _; _ -> false")
        public boolean equals(final @Nullable Object object) {
            return object == this || object instanceof SingleVersion version && version.major() == major;
        }

        /**
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("-> const")
        public @Range(from = 0, to = Integer.MAX_VALUE) int hashCode() {
            return major;
        }

        /**
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("-> const")
        public @NotNull String toString() {
            return Integer.toString(major);
        }

    }

}
