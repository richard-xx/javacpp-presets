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


@Namespace("c10::cuda::CUDACachingAllocator") @Properties(inherit = org.bytedeco.pytorch.presets.torch_cuda.class)
public class CUDAAllocator extends Allocator {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public CUDAAllocator(Pointer p) { super(p); }

  public native Pointer raw_alloc(@Cast("size_t") long nbytes);
  public native Pointer raw_alloc_with_stream(@Cast("size_t") long nbytes, @Cast("cudaStream_t") Pointer stream);
  public native void raw_delete(Pointer ptr);
  public native void init(int device_count);
  public native @Cast("bool") boolean initialized();
  public native void setMemoryFraction(double fraction, int device);
  public native void emptyCache();
  public native void cacheInfo(int dev_id, @Cast("size_t*") SizeTPointer largestBlock);
  public native Pointer getBaseAllocation(Pointer ptr, @Cast("size_t*") SizeTPointer size);
  public native void recordStream(@Cast({"", "c10::DataPtr&&"}) @StdMove DataPtr arg0, @ByVal CUDAStream stream);
  public native @ByVal DeviceStats getDeviceStats(int device);
  public native void resetAccumulatedStats(int device);
  public native void resetPeakStats(int device);
  public native @ByVal SnapshotInfo snapshot();
  public native void beginAllocateStreamToPool(
        int device,
        @Cast("cudaStream_t") Pointer stream,
        @ByVal @Cast("c10::cuda::MempoolId_t*") DeviceAssertionsDataVectorCUDAKernelLaunchInfoVectorPair mempool_id);
  public native void endAllocateStreamToPool(int device, @Cast("cudaStream_t") Pointer stream);
  public native void releasePool(int device, @ByVal @Cast("c10::cuda::MempoolId_t*") DeviceAssertionsDataVectorCUDAKernelLaunchInfoVectorPair mempool_id);
  // returns true if the allocated blocks are equal to expected live allocations
  public native @Cast("bool") boolean checkPoolLiveAllocations(
        int device,
        @ByVal @Cast("c10::cuda::MempoolId_t*") DeviceAssertionsDataVectorCUDAKernelLaunchInfoVectorPair mempool_id,
        @Const @ByRef PointerSet expected_live_allocations);
  public native @SharedPtr Pointer getIpcDevPtr(@StdString BytePointer handle);
  public native @SharedPtr Pointer getIpcDevPtr(@StdString String handle);
  public native @Cast("bool") boolean isHistoryEnabled();
  public native void recordHistory(
        @Cast("bool") boolean enabled,
        @ByVal @Cast("c10::cuda::CUDACachingAllocator::CreateContextFn*") Pointer context_recorder,
        @Cast("size_t") long alloc_trace_max_entries,
        RecordContext when);
  public native void recordHistory(
        @Cast("bool") boolean enabled,
        @ByVal @Cast("c10::cuda::CUDACachingAllocator::CreateContextFn*") Pointer context_recorder,
        @Cast("size_t") long alloc_trace_max_entries,
        @Cast("c10::cuda::CUDACachingAllocator::RecordContext") int when);
  public native void attachOutOfMemoryObserver(@ByVal @Cast("c10::cuda::CUDACachingAllocator::OutOfMemoryObserver*") Pointer observer);

  public native void enablePeerAccess(int dev, int dev_to_access);

  // memory not allocated from cudaMalloc cannot be copied
  // across devices using cudaMemcpyAsync if peer to peer access is disabled.
  // instead it requres cudaMemcpyAsyncPeer
  //  with P2P Enabled, all combinations work
  //  with P2P Disabled:
  //                       cudaMalloc cudaMallocAsync/cuMemMap
  // cudaMemcpyAsyncPeer   works      works
  // cudaMemcpyAsync       works      error

  // This function performs chooses to use the Peer version of
  // memcpy if required based on where the allocated put dst/src.
  public native @Cast("cudaError_t") int memcpyAsync(
        Pointer dst,
        int dstDevice,
        @Const Pointer src,
        int srcDevice,
        @Cast("size_t") long count,
        @Cast("cudaStream_t") Pointer stream,
        @Cast("bool") boolean p2p_enabled);
  public native @SharedPtr("c10::cuda::CUDACachingAllocator::AllocatorState") @ByVal AllocatorState getCheckpointState(
        int device,
        @ByVal @Cast("c10::cuda::MempoolId_t*") DeviceAssertionsDataVectorCUDAKernelLaunchInfoVectorPair id);
  public native @ByVal CheckpointDelta setCheckpointPoolState(
        int device,
        @SharedPtr("c10::cuda::CUDACachingAllocator::AllocatorState") @ByVal AllocatorState pps);
  public native @StdString BytePointer name();
}
