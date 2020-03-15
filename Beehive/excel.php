<?php
/**
 * PHPExcel
 *
 * Copyright (C) 2006 - 2014 PHPExcel
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @category   PHPExcel
 * @package    PHPExcel
 * @copyright  Copyright (c) 2006 - 2014 PHPExcel (http://www.codeplex.com/PHPExcel)
 * @license    http://www.gnu.org/licenses/old-licenses/lgpl-2.1.txt	LGPL
 * @version    1.8.0, 2014-03-02
 */

/** Error reporting */
error_reporting(E_ALL);
ini_set('display_errors', TRUE);
ini_set('display_startup_errors', TRUE);
date_default_timezone_set('Europe/London');

if (PHP_SAPI == 'cli')
	die('This example should only be run from a Web Browser');

/** Include PHPExcel */
require_once dirname(__FILE__) . '/Classes/PHPExcel.php';


// Create new PHPExcel object
$objPHPExcel = new PHPExcel();

// Set document properties
$objPHPExcel->getProperties()->setCreator("Maarten Balliauw")
							 ->setLastModifiedBy("Maarten Balliauw")
							 ->setTitle("Office 2007 XLSX Test Document")
							 ->setSubject("Office 2007 XLSX Test Document")
							 ->setDescription("Test document for Office 2007 XLSX, generated using PHP classes.")
							 ->setKeywords("office 2007 openxml php")
							 ->setCategory("Test result file");


// Add feilds
$objPHPExcel->setActiveSheetIndex(0)
            ->setCellValue('A1', 'Observation ID')
            ->setCellValue('B1', 'Hive Name')
            ->setCellValue('C1', 'Observation Date')
            ->setCellValue('D1', 'Duration(days)')
            ->setCellValue('E1', 'Mite Count')
            ->setCellValue('F1', 'Submition Date');


    $db;

    $username = "joel_db";
    $password = "joel123";
    $hostname = "localhost";
    $database = "joel_bee_database";
    try {
        //Instantiate a database object
        $db = new PDO("mysql:host=$hostname; dbname=$database", $username, $password);
    } catch(PDOException $e) {
        echo $e->getMessage();
    }
        
    include "Models/observationModel.php";

    $obsModel = new ObservationModel($db);
    $obsList = $obsModel->getAllObservations();
$rowNum = 2;
foreach($obsList as $row) {
        
// entering data
$objPHPExcel->setActiveSheetIndex(0)
            ->setCellValue("A$rowNum", $row['observation_id'])
            ->setCellValue("B$rowNum", $row['hive_name'])
            ->setCellValue("C$rowNum", $row['observation_date'])
            ->setCellValue("D$rowNum", $row['duration'])
            ->setCellValue("E$rowNum", $row['mite_count'])
            ->setCellValue("F$rowNum", $row['submit_date']);
            $rowNum++;
}

// Rename worksheet
$objPHPExcel->getActiveSheet()->setTitle('Observations');


// Set active sheet index to the first sheet, so Excel opens this as the first sheet
$objPHPExcel->setActiveSheetIndex(0);


// Redirect output to a client’s web browser (Excel2007)
header('Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
header('Content-Disposition: attachment;filename="observations.xlsx"');
header('Cache-Control: max-age=0');
// If you're serving to IE 9, then the following may be needed
header('Cache-Control: max-age=1');

// If you're serving to IE over SSL, then the following may be needed
header ('Expires: Mon, 26 Jul 1997 05:00:00 GMT'); // Date in the past
header ('Last-Modified: '.gmdate('D, d M Y H:i:s').' GMT'); // always modified
header ('Cache-Control: cache, must-revalidate'); // HTTP/1.1
header ('Pragma: public'); // HTTP/1.0

$objWriter = PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel2007');
$objWriter->save('php://output');
exit;
