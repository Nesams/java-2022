package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.CPU;
import ee.taltech.iti0202.computerbuilder.components.ComputerCase;
import ee.taltech.iti0202.computerbuilder.components.GPU;
import ee.taltech.iti0202.computerbuilder.components.HDD;
import ee.taltech.iti0202.computerbuilder.components.MotherBoard;
import ee.taltech.iti0202.computerbuilder.components.PSU;
import ee.taltech.iti0202.computerbuilder.components.RAM;
import ee.taltech.iti0202.computerbuilder.components.SSD;


public class Computer {
    private ComputerCase computerCase;
    private CPU cpu;
    private GPU gpu;
    private HDD hdd;
    private MotherBoard motherBoard;
    private PSU psu;
    private RAM ram;
    private SSD ssd;
    private int id;
    private static int idCount = 0;

    public Computer() {
        this.id = idCount;
        this.computerCase = null;
        this.cpu = null;
        this.gpu = null;
        this.hdd = null;
        this.motherBoard = null;
        this.ram = null;
        this.ssd = null;
        idCount++;
    }
    public int getId() {
        return id;
    }

    public void setComputerCase(ComputerCase computerCase) {
        this.computerCase = computerCase;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public void setPsu(PSU psu) {
        this.psu = psu;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public void setSsd(SSD ssd) {
        this.ssd = ssd;
    }

    public int getComputerValue() {
        if (hdd == null) {
            return computerCase.getPrice() + cpu.getPrice() + gpu.getPrice()
                    + motherBoard.getPrice() + ram.getPrice() + ssd.getPrice();
        }
        if (ssd == null) {
            return computerCase.getPrice() + cpu.getPrice() + gpu.getPrice()
                    + hdd.getPrice() + motherBoard.getPrice() + ram.getPrice();

        }
        return computerCase.getPrice() + cpu.getPrice() + gpu.getPrice()
                + hdd.getPrice() + motherBoard.getPrice() + ram.getPrice() + ssd.getPrice();
    }
}
