/*
 * Copyright 2021 Bundesreplublik Deutschland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ibm.ssi.controller.company.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.ibm.ssi.controller.company.service.dto.AddressDTO;
import com.ibm.ssi.controller.company.service.dto.EmployeeDTO;
import com.ibm.ssi.controller.company.service.exceptions.InvalidCSVFileException;
import com.ibm.ssi.controller.company.service.mapper.EmployeeMapper;
import com.opencsv.CSVIterator;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVToEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public List<EmployeeDTO> readEmployeesCSV(MultipartFile file) throws InvalidCSVFileException {

        try {
            List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();

            CSVIterator iterator = new CSVIterator(csvReader);
            while (iterator.hasNext()) {
                String[] entry = iterator.next();

                String[] fullName = entry[0].split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setLastName(fullName[0].trim());
                employeeDTO.setFirstName(fullName[1].trim());
                employeeDTO.setEmail(entry[1]);
                employeeDTO.setFirmName(entry[4]);
                employeeDTO.setFirmSubject(entry[2]);
                employeeDTO.setFirmStreet(entry[5]);
                employeeDTO.setFirmPostalCode(entry[6]);
                employeeDTO.setFirmCity(entry[7]);

                // auto generate an id
                employeeDTO.setEmployeeId(RandomStringUtils.randomAlphanumeric(8));

                employeeDTOs.add(employeeDTO);
            }
            return employeeDTOs;
        } catch (IOException | CsvValidationException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidCSVFileException();
        }
    }
}
