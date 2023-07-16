package com.ncorti.ktfmt.gradle

import com.facebook.ktfmt.format.FormattingOptions
import com.facebook.ktfmt.format.FormattingOptions.Companion.DEFAULT_MAX_WIDTH
import com.facebook.ktfmt.format.FormattingOptions.Style as KtfmtStyle
import java.io.Serializable

internal data class FormattingOptionsBean(

    /** The style used by ktfmt */
    val style: Style = Style.FACEBOOK,

    /** ktfmt breaks lines longer than maxWidth. */
    val maxWidth: Int = DEFAULT_MAX_WIDTH,

    /**
     * blockIndent is the size of the indent used when a new block is opened, in spaces.
     *
     * For example,
     * ```
     * fun f() {
     *   //
     * }
     * ```
     */
    val blockIndent: Int = 2,

    /**
     * continuationIndent is the size of the indent used when a line is broken because it's too
     * long, in spaces.
     *
     * For example,
     * ```
     * val foo = bar(
     *     1)
     * ```
     */
    val continuationIndent: Int = 4,

    /** Whether ktfmt should remove imports that are not used. */
    val removeUnusedImports: Boolean = true,

    /**
     * Print the Ops generated by KotlinInputAstVisitor to help reason about formatting (i.e.,
     * newline) decisions
     */
    val debuggingPrintOpsAfterFormatting: Boolean = false
) : Serializable {
    fun toFormattingOptions(): FormattingOptions =
        FormattingOptions(
            style = style.toKtfmtStyle(),
            maxWidth = maxWidth,
            blockIndent = blockIndent,
            continuationIndent = continuationIndent,
            removeUnusedImports = removeUnusedImports,
            debuggingPrintOpsAfterFormatting = debuggingPrintOpsAfterFormatting
        )

    enum class Style {
        FACEBOOK,
        DROPBOX,
        GOOGLE;

        fun toKtfmtStyle(): KtfmtStyle =
            when (this) {
                FACEBOOK -> KtfmtStyle.FACEBOOK
                DROPBOX -> KtfmtStyle.DROPBOX
                GOOGLE -> KtfmtStyle.GOOGLE
            }
    }

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
