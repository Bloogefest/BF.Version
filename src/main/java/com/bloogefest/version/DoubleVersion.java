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
 * Этот интерфейс содержит методы, применимые к двойному формату версий.
 *
 * @see Impl
 * @see #of(int)
 * @see #of(int, int)
 * @since 1.0.0-RC1
 */
public interface DoubleVersion extends SingleVersion {

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
     * @see #of(int, int)
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    static @NotNull DoubleVersion of(final @Range(from = 0, to = Integer.MAX_VALUE) int major) {
        return new Impl(major);
    }

    /**
     * Создаёт и возвращает
     * {@linkplain Impl#Impl(int, int) встроенную реализацию на основе переданного главного и второстепенного номеров
     * версии}.
     *
     * @param major главный номер версии.
     *
     * @return {@linkplain Impl#Impl(int, int) Встроенную реализацию на основе переданного главного и второстепенного
     * номеров версии}.
     *
     * @see Impl
     * @see Impl#Impl(int, int)
     * @see #of(int)
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    static @NotNull DoubleVersion of(final @Range(from = 0, to = Integer.MAX_VALUE) int major,
                                     final @Range(from = 0, to = Integer.MAX_VALUE) int minor) {
        return new Impl(major, minor);
    }

    /**
     * Возвращает второстепенный номер версии.
     *
     * @return Второстепенный номер версии.
     *
     * @since 1.0.0-RC1
     */
    @Contract("-> const")
    @Range(from = 0, to = Integer.MAX_VALUE) int minor();

    /**
     * Возвращает истинное значение, если переданная версия двойного формата новее текущей, в противном случае — ложное
     * значение.
     *
     * @param version версия двойного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(DoubleVersion)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean newer(final @NotNull DoubleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version").major() > major() ||
               version.major() == major() && version.minor() > minor();
    }

    /**
     * Возвращает истинное значение, если переданная версия одиночного формата новее текущей, в противном случае —
     * ложное значение.
     *
     * @param version версия одиночного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean newer(final @NotNull SingleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof DoubleVersion version_ && older(version_) ||
               version.major() > major();
    }

    /**
     * Если формат переданной версии является двойным и если переданная версия новее текущей, то возвращает истинное
     * значение, в противном случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #same(DoubleVersion)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> _; _ -> fail")
    default boolean newer(final @NotNull Version version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof DoubleVersion version_ && newer(version_) ||
               version instanceof SingleVersion version__ && newer(version__);
    }

    /**
     * Возвращает истинное значение, если переданная версия двойного формата такая же, как и текущая, в противном случае
     * — ложное значение.
     *
     * @param version версия двойного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean same(final @NotNull DoubleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version").major() == major() && version.minor() == minor();
    }

    /**
     * Возвращает истинное значение, если переданная версия одиночного формата такая же, как и текущая, в противном
     * случае — ложное значение.
     *
     * @param version версия одиночного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean same(final @NotNull SingleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof DoubleVersion version_ && same(version_) ||
               minor() == 0 && version.major() == major();
    }

    /**
     * Возвращает истинное значение, если переданная версия такая же, как и текущая и её формат является двойным, в
     * противном случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(DoubleVersion)
     * @see #same(SingleVersion)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> _; _ -> fail")
    default boolean same(final @NotNull Version version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof DoubleVersion version_ && same(version_) ||
               version instanceof SingleVersion version__ && same(version__);
    }

    /**
     * Возвращает истинное значение, если переданная версия двойного формата старее текущей, в противном случае — ложное
     * значение.
     *
     * @param version версия двойного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean older(final @NotNull DoubleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version").major() < major() ||
               version.major() == major() && version.minor() < minor();
    }

    /**
     * Возвращает истинное значение, если переданная версия одиночного формата старее текущей, в противном случае —
     * ложное значение.
     *
     * @param version версия одиночного формата.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Contract("!null -> _; _ -> fail")
    default boolean older(final @NotNull SingleVersion version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof DoubleVersion version_ && older(version_) ||
               version.major() < major();
    }

    /**
     * Возвращает истинное значение, если переданная версия старее текущей и её формат является двойным, в противном
     * случае — ложное значение.
     *
     * @param version версия.
     *
     * @return Истинное или ложное значение.
     *
     * @see #newer(DoubleVersion)
     * @see #newer(SingleVersion)
     * @see #newer(Version)
     * @see #same(DoubleVersion)
     * @see #same(SingleVersion)
     * @see #same(Version)
     * @see #older(DoubleVersion)
     * @see #older(SingleVersion)
     * @see #older(Version)
     * @since 1.0.0-RC1
     */
    @Override
    @Contract("!null -> _; _ -> fail")
    default boolean older(final @NotNull Version version) throws NullException {
        return Validator.notNull(version, "The passed version") instanceof DoubleVersion version_ && older(version_) ||
               version instanceof SingleVersion version__ && older(version__);
    }

    /**
     * Встроенная реализация.
     *
     * @see DoubleVersion
     * @see #Impl(int)
     * @see #Impl(int, int)
     * @since 1.0.0-RC1
     */
    class Impl extends SingleVersion.Impl implements DoubleVersion {

        /**
         * Второстепенный номер версии.
         *
         * @since 1.0.0-RC1
         */
        protected final @Range(from = 0, to = Integer.MAX_VALUE) int minor;

        /**
         * Создаёт встроенную реализацию на основе переданного главного номера версии.
         *
         * @param major главный номер версии.
         *
         * @since 1.0.0-RC1
         */
        public Impl(@Range(from = 0, to = Integer.MAX_VALUE) final int major) {
            this(major, 0);
        }

        /**
         * Создаёт встроенную реализацию на основе переданного главного и второстепенного номеров версии.
         *
         * @param major главный номер версии.
         * @param minor второстепенный номер версии.
         *
         * @since 1.0.0-RC1
         */
        public Impl(@Range(from = 0, to = Integer.MAX_VALUE) final int major,
                    @Range(from = 0, to = Integer.MAX_VALUE) final int minor) {
            super(major);
            this.minor = minor;
        }

        /**
         * Возвращает {@linkplain #minor второстепенный номер версии}.
         *
         * @return {@linkplain #minor второстепенный номер версии}.
         *
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("-> const")
        public @Range(from = 0, to = Integer.MAX_VALUE) int minor() {
            return minor;
        }

        /**
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("!null -> _; _ -> false")
        public boolean equals(final @Nullable Object object) {
            return object == this ||
                   object instanceof DoubleVersion version && version.major() == major && version.minor() == minor ||
                   minor == 0 && object instanceof SingleVersion version_ && version_.major() == major;
        }

        /**
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("-> const")
        public @Range(from = 0, to = Integer.MAX_VALUE) int hashCode() {
            return 31 * super.hashCode() + minor;
        }

        /**
         * @since 1.0.0-RC1
         */
        @Override
        @Contract("-> const")
        public @NotNull String toString() {
            return super.toString() + '.' + minor;
        }

    }

}
