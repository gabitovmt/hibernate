/**
 * Список всех стратегий смотрите в книге
 * К.Бауэр, Г. Кинг, Г. Грегори 'Java Persistence API и Hibernate'
 * на стр. 100-103.
 */
@org.hibernate.annotations.GenericGenerators({
        @org.hibernate.annotations.GenericGenerator(
                name = "ID_GENERATOR",
                // Производит последовательные числовые значения.
                // Если диалект SQL поддерживает последовательности, то
                // будут использоваться настоящие последовательности,
                // в противном случае, Hibernate создаст "таблицу последовательностей".
                strategy = "enhanced-sequence",
                parameters = {
                        @org.hibernate.annotations.Parameter(
                                name = "sequence_name",     // Имя последовательности
                                value = "CUSTOME_GENERIC_SEQUENCE"
                        ),
                        @org.hibernate.annotations.Parameter(
                                name = "initial_value",     // Начальное значение
                                value = "1000"
                        )
                }
        ),
        @org.hibernate.annotations.GenericGenerator(
                name = "UUID",
                // Создаёт уникальный 128-битный идентификатор UUID на уровне приложения.
                // UUID используют, если требуется сливать данные с нескольких БД в один пакет.
                // Тип идентификатора: java.lang.String
                strategy = "uuid"
        ),
        @org.hibernate.annotations.GenericGenerator(
                name = "UUID_HEX_GENERATOR",
                // Создаёт уникальный 128-битный идентификатор UUID на уровне приложения.
                // UUID используют, если требуется сливать данные с нескольких БД в один пакет.
                // Тип идентификатора: java.lang.String
                strategy = "org.hibernate.id.UUIDGenerator"
        ),
        @org.hibernate.annotations.GenericGenerator(
                name = "UUID_GENERATOR",
                // Создаёт уникальный 128-битный идентификатор UUID на уровне приложения.
                // UUID используют, если требуется сливать данные с нескольких БД в один пакет.
                // Тип идентификатора: java.util.UUID
                strategy = "org.hibernate.id.UUIDGenerator"
        ),
        @org.hibernate.annotations.GenericGenerator(
                name = "GUID_GENERATOR",
                // Использует глобально уникальный идентификатор, созданный БД при помощи SQL-функции.
                // GUID используют, если требуется сливать данные с нескольких БД в один пакет.
                strategy = "guid"
        )
})
package com.example.model.generatedvalue;