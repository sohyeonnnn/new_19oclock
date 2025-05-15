package com.refactoring.ilgusi.domain.service;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ServiceFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_file_seq_gen")
    @SequenceGenerator(name = "service_file_seq_gen", sequenceName = "SERVICE_FILE_SEQ", allocationSize = 1)
    private Integer fileNo;

    @Column
    private String filename;
    @Column
    private String filepath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_no")
    private ServiceItem service;

    @Override
    public String toString() {
        return "ServiceFile{" +
                "fileNo=" + fileNo +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
