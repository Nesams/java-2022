package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.*;

public class PC extends Computer {

    private final ComputerCase computerCase;
    private final HDD hdd;
    private final SSD ssd;
    private final CPU cpu;
    private final GPU gpu;
    private final RAM ram;
    private final MotherBoard motherBoard;
    private final PSU psu;

    public PC() {
    super();
    this.hdd = null;
    this.ssd = null;
    this.computerCase = null;
    this.cpu = null;
    this.gpu = null;
    this.ram = null;
    this.motherBoard = null;
    this.psu = null;

}

    @Override
    public HDD getHdd() {
        return hdd;
    }

    @Override
    public SSD getSsd() {
        return ssd;
    }

    @Override
    public ComputerCase getComputerCase() {
        return computerCase;
    }

    @Override
    public CPU getCpu() {
        return cpu;
    }

    @Override
    public GPU getGpu() {
        return gpu;
    }

    @Override
    public RAM getRam() {
        return ram;
    }

    @Override
    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    @Override
    public PSU getPsu() {
        return psu;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setComputerCase(ComputerCase computerCase) {
        super.setComputerCase(computerCase);
    }

    @Override
    public void setCpu(CPU cpu) {
        super.setCpu(cpu);
    }

    @Override
    public void setRam(RAM ram) {
        super.setRam(ram);
    }

    @Override
    public void setGpu(GPU gpu) {
        super.setGpu(gpu);
    }

    @Override
    public void setHdd(HDD hdd) {
        super.setHdd(hdd);
    }

    @Override
    public void setMotherBoard(MotherBoard motherBoard) {
        super.setMotherBoard(motherBoard);
    }

    @Override
    public void setPsu(PSU psu) {
        super.setPsu(psu);
    }

    @Override
    public void setSsd(SSD ssd) {
        super.setSsd(ssd);
    }

    @Override
    public int getComputerValue() {
        return super.getComputerValue();
    }
}
