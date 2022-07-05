package paolo.disney.foundation.mappers


abstract class UnaryMapper<T1, T2> {

    abstract fun map(values: T1): T2

}