package com.example.planpal

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.planpal.databinding.ActivityMainBinding // 1. Import the generated binding class

class MainActivity : AppCompatActivity() {

    // 2. Declare the binding variable
    private lateinit var binding: ActivityMainBinding

    // 1. Define Document Size Constants
    private val SIZE_A4 = "A4 (210 x 297 mm)"
    private val SIZE_LETTER = "Letter (8.5 x 11 in)"
    private val SIZE_LEGAL = "Legal (8.5 x 14 in)"
    private val SIZE_A5 = "A5 (148 x 210 mm)"
    private val SIZE_CUSTOM = "Custom Page..."

    // List of sizes to display in the dialog
    private val documentSizes = arrayOf(SIZE_A4, SIZE_LETTER, SIZE_LEGAL, SIZE_A5, SIZE_CUSTOM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3. Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 4. Set the content view using the binding root
        setContentView(binding.root)

        setupClickListeners()
    }

    // --- Click Listeners Setup (Using Binding) ---

    private fun setupClickListeners() {
        // Access the FAB directly through the binding object
        binding.fabCreate.setOnClickListener {
            showDocumentSizeSelectionDialog()
        }

        // Example: Binding for the Files navigation (assuming you name the LinearLayout container)
        // If your layout has IDs on the nav containers, you can access them here:
        // binding.bottomNav.filesContainer.setOnClickListener { /* Handle Files Click */ }
    }

    // --- Dialog and Creation Flow (No Changes Needed Here) ---

    /**
     * Shows a dialog to the user prompting them to select a document size.
     */
    private fun showDocumentSizeSelectionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Select Document Size")
            .setItems(documentSizes) { dialog, which ->
                val selectedSize = documentSizes[which]

                if (selectedSize == SIZE_CUSTOM) {
                    showCustomSizeDialog()
                } else {
                    startDocumentCreation(selectedSize)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                Toast.makeText(this, "Document creation cancelled.", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

    /**
     * Shows a separate dialog prompting the user to input custom dimensions.
     */
    private fun showCustomSizeDialog() {
        val input = EditText(this)
        input.hint = "e.g., 10 x 15 cm or 5 x 8 in"

        AlertDialog.Builder(this)
            .setTitle("Enter Custom Dimensions")
            .setMessage("Please enter size (e.g., Width x Height Unit)")
            .setView(input)
            .setPositiveButton("Create") { dialog, _ ->
                val customDimensions = input.text.toString().trim()
                if (customDimensions.isNotEmpty()) {
                    startDocumentCreation("Custom: $customDimensions")
                } else {
                    Toast.makeText(this, "Custom size cannot be empty.", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                Toast.makeText(this, "Custom size creation cancelled.", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

    /**
     * Placeholder function to initiate the actual document creation process
     * by passing the selected size to the next activity.
     */
    private fun startDocumentCreation(size: String) {
        Toast.makeText(this, "Starting new document with size: $size", Toast.LENGTH_LONG).show()

        // Example of launching the next activity (DocumentEditorActivity):
        /*
        val intent = Intent(this, DocumentEditorActivity::class.java)
        intent.putExtra("DOCUMENT_SIZE", size)
        startActivity(intent)
        */
    }
}