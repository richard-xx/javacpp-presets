// Targeted by JavaCPP version 1.5.10-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.pytorch.cuda;

import org.bytedeco.pytorch.*;
import org.bytedeco.pytorch.functions.*;
import org.bytedeco.pytorch.Error;
import org.bytedeco.pytorch.global.torch.DeviceType;
import org.bytedeco.pytorch.global.torch.ScalarType;
import org.bytedeco.pytorch.global.torch.MemoryFormat;
import org.bytedeco.pytorch.Allocator;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;
import org.bytedeco.pytorch.*;
import static org.bytedeco.pytorch.global.torch.*;

import static org.bytedeco.pytorch.global.torch_cuda.*;


@Namespace("at::native") @Properties(inherit = org.bytedeco.pytorch.presets.torch_cuda.class)
public class ConvolutionDescriptor extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public ConvolutionDescriptor() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public ConvolutionDescriptor(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ConvolutionDescriptor(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public ConvolutionDescriptor position(long position) {
        return (ConvolutionDescriptor)super.position(position);
    }
    @Override public ConvolutionDescriptor getPointer(long i) {
        return new ConvolutionDescriptor((Pointer)this).offsetAddress(i);
    }

  public native void set(@Cast("cudnnDataType_t") int dataType, int dim, IntPointer pad, IntPointer stride, IntPointer upscale, int groups, @Cast("bool") boolean allow_tf32);
  public native void set(@Cast("cudnnDataType_t") int dataType, int dim, IntBuffer pad, IntBuffer stride, IntBuffer upscale, int groups, @Cast("bool") boolean allow_tf32);
  public native void set(@Cast("cudnnDataType_t") int dataType, int dim, int[] pad, int[] stride, int[] upscale, int groups, @Cast("bool") boolean allow_tf32);
}
