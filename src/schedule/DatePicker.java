/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

/**
 *
 * @author DELL
 */
/*
 * Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

// editor and converter code in sync with ComboBox 4858:e60e9a5396e6

import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.WritableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableBooleanProperty;
import javafx.css.StyleableProperty;
import javafx.scene.AccessibleAttribute;
import javafx.scene.AccessibleRole;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import com.sun.javafx.css.converters.BooleanConverter;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import com.sun.javafx.scene.control.skin.resources.ControlResources;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Control;
import javafx.scene.control.DateCell;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;


/**
 * The DatePicker control allows the user to enter a date as text or
 * to select a date from a calendar popup. The calendar is based on
 * either the standard ISO-8601 chronology or any of the other
 * chronology classes defined in the java.time.chrono package.
 *
 * <p>The {@link #valueProperty() value} property represents the
 * currently selected {@link java.time.LocalDate}.  An initial date can
 * be set via the {@link #DatePicker(java.time.LocalDate) constructor}
 * or by calling {@link #setValue(java.time.LocalDate) setValue()}.  The
 * default value is null.
 *
 * <pre><code>
 * final DatePicker datePicker = new DatePicker();
 * datePicker.setOnAction(new EventHandler() {
 *     public void handle(Event t) {
 *         LocalDate date = datePicker.getValue();
 *         System.err.println("Selected date: " + date);
 *     }
 * });
 * </code></pre>
 *
 * The {@link #chronologyProperty() chronology} property specifies a
 * calendar system to be used for parsing, displaying, and choosing
 * dates.
 * The {@link #valueProperty() value} property is always defined in
 * the ISO calendar system, however, so applications based on a
 * different chronology may use the conversion methods provided in the
 * {@link java.time.chrono.Chronology} API to get or set the
 * corresponding {@link java.time.chrono.ChronoLocalDate} value. For
 * example:
 *
 * <pre><code>
 * LocalDate isoDate = datePicker.getValue();
 * ChronoLocalDate chronoDate =
 *     ((isoDate != null) ? datePicker.getChronology().date(isoDate) : null);
 * System.err.println("Selected date: " + chronoDate);
 * </code></pre>
 *
 *
 * @since JavaFX 8.0
 */
public class DatePicker extends javafx.scene.control.DatePicker {
   
}
