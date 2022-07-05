package paolo.disney.foundation.extensions


fun String.isAboveExpectedLength(maximumLength: Int): Boolean {
    return this.length > maximumLength
}