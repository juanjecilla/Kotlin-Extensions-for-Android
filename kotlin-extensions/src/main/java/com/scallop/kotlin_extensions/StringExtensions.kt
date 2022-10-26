package com.scallop.kotlin_extensions


fun String?.valid(): Boolean =
    this != null && !this.equals("null", true)
            && this.trim().isNotEmpty()

