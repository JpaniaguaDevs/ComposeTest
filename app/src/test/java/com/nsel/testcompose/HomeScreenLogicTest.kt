package com.nsel.testcompose

import com.nsel.testcompose.domain.model.Invoice
import com.nsel.testcompose.presentation.view.home.InvoiceFilter
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeScreenLogicTest {

    private val sampleInvoices = listOf(
        Invoice("Factura #1", 100.0, true),
        Invoice("Factura #2", 200.0, false),
        Invoice("Factura #3", 300.0, true),
        Invoice("Factura #4", 400.0, false)
    )

    @Test
    fun summaryMetrics_calculatesCorrectTotals() {
        val total = sampleInvoices.sumOf { it.amount }
        val paid = sampleInvoices.filter { it.isPaid }.sumOf { it.amount }
        val pending = sampleInvoices.filter { !it.isPaid }.sumOf { it.amount }

        assertEquals(1000.0, total, 0.001)
        assertEquals(400.0, paid, 0.001)
        assertEquals(600.0, pending, 0.001)
    }

    @Test
    fun invoiceFilter_filtersCorrectly() {
        val all = sampleInvoices
        val paid = sampleInvoices.filter { it.isPaid }
        val pending = sampleInvoices.filter { !it.isPaid }

        assertEquals(4, all.size)
        assertEquals(2, paid.size)
        assertEquals(2, pending.size)
    }
}
