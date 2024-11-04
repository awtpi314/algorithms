package com.awtpi314;

public class VehicleNode {
  private boolean isVertical;
  private String vehicleName;

  public VehicleNode(boolean isVertical, String vehicleName) {
    this.isVertical = isVertical;
    this.vehicleName = vehicleName;
  }

  public boolean getIsVertical() {
    return isVertical;
  }

  public String getVehicleName() {
    return vehicleName;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof VehicleNode && ((VehicleNode) obj).isVertical == isVertical
        && ((VehicleNode) obj).vehicleName.equals(vehicleName);
  }
}
